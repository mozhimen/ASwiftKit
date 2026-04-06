package com.mozhimen.basick.bases

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.basick.utils.runOnMainThread
import com.mozhimen.kotlin.lintk.optins.api.OApiInit_ByLazy
import com.mozhimen.kotlin.lintk.optins.api.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.api.OApiCall_BindViewLifecycle


/**
 * @ClassName BaseReceiverProxy
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Version 1.0
 */
@OApiCall_BindViewLifecycle
@OApiCall_BindLifecycle
@OApiInit_ByLazy
open class BaseBroadcastReceiverProxy : BaseWakeBefDestroyLifecycleObserver {
    protected val _context: Context
    protected val _owner: LifecycleOwner
    protected val _receiver: BroadcastReceiver
    protected val _actions: Array<out String>

    /////////////////////////////////////////////////////////////////////////////

    constructor(context: Context, owner: LifecycleOwner, receiver: BroadcastReceiver, actions: Array<out String>) : super() {
        _context = context
        _owner = owner
        _receiver = receiver
        _actions = actions
        _owner.runOnMainThread{registerReceiver()}
    }

    /////////////////////////////////////////////////////////////////////////////

    override fun onDestroy(owner: LifecycleOwner) {
        _context.unregisterReceiver(_receiver)
        super.onDestroy(owner)
    }

    /////////////////////////////////////////////////////////////////////////////

    protected open fun registerReceiver() {
        val intentFilter = IntentFilter()
        if (_actions.isNotEmpty()) for (action in _actions) intentFilter.addAction(action)
        _context.registerReceiver(_receiver, intentFilter)
    }
}