package com.mozhimen.basick.bases

import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.basick.utils.runOnMainThread
import com.mozhimen.kotlin.lintk.optins.api.OApiInit_ByLazy
import com.mozhimen.kotlin.elemk.androidx.lifecycle.commons.IDefaultLifecycleObserver
import com.mozhimen.kotlin.lintk.optins.api.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.api.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.utilk.commons.IUtilK

/**
 * @ClassName BaseWakeBefDestroyLifecycleObserver
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/11/21 21:22
 * @Version 1.0
 */
@OApiCall_BindViewLifecycle
@OApiCall_BindLifecycle
@OApiInit_ByLazy
open class BaseWakeBefDestroyLifecycleObserver : IDefaultLifecycleObserver, IUtilK {

    protected var _boundLifecycle: Lifecycle? = null

    protected fun isLifecycleBound(owner: LifecycleOwner): Boolean =
        _boundLifecycle === owner.lifecycle

    @MainThread
    protected fun unbindLifecycle() {
        _boundLifecycle?.removeObserver(this)
        _boundLifecycle = null
    }

    @CallSuper
    override fun bindLifecycle(owner: LifecycleOwner) {
        val newLifecycle = owner.lifecycle

        if (newLifecycle.currentState == Lifecycle.State.DESTROYED) {
            return
        }

        if (_boundLifecycle === newLifecycle) {
            return
        }

        owner.runOnMainThread {
            unbindLifecycle()

            newLifecycle.addObserver(this@BaseWakeBefDestroyLifecycleObserver)
            _boundLifecycle = newLifecycle
        }
    }

    @CallSuper
    override fun onDestroy(owner: LifecycleOwner) {
        if (_boundLifecycle === owner.lifecycle) {
            unbindLifecycle()
        } else {
            // 防止特殊情况下当前回调来自旧 Lifecycle
            owner.lifecycle.removeObserver(this@BaseWakeBefDestroyLifecycleObserver)
        }
        super.onDestroy(owner)
    }
}