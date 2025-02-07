package com.mozhimen.basick.bases

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.basick.utils.runOnMainThread
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindViewLifecycle


/**
 * @ClassName BaseReceiverProxy
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Version 1.0
 */
@OApiCall_BindViewLifecycle
@OApiCall_BindLifecycle
@OApiInit_ByLazy
open class BaseBroadcastReceiverProxy<C> : BaseWakeBefDestroyLifecycleObserver where C : Context, C : LifecycleOwner {
    protected val _activity: C
    protected val _receiver: BroadcastReceiver
    protected val _actions: Array<out String>

    /////////////////////////////////////////////////////////////////////////////

    constructor(activity: C, receiver: BroadcastReceiver, actions: Array<out String>) : super() {
        _activity = activity
        _receiver = receiver
        _actions = actions
        _activity.runOnMainThread{registerReceiver()}
    }

    /////////////////////////////////////////////////////////////////////////////

    override fun onDestroy(owner: LifecycleOwner) {
        _activity.unregisterReceiver(_receiver)
        super.onDestroy(owner)
    }

    /////////////////////////////////////////////////////////////////////////////

    protected open fun registerReceiver() {
        val intentFilter = IntentFilter()
        if (_actions.isNotEmpty()) for (action in _actions) intentFilter.addAction(action)
        _activity.registerReceiver(_receiver, intentFilter)
    }
}