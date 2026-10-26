package com.mozhimen.basick.impls.proxys

import android.app.Activity
import android.app.Dialog
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.basick.bases.BaseWakeBefDestroyLifecycleObserver
import com.mozhimen.kotlin.lintk.optins.api.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.api.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.lintk.optins.api.OApiInit_ByLazy

@OptIn(OApiCall_BindViewLifecycle::class, OApiCall_BindLifecycle::class)
@OApiInit_ByLazy
abstract class DialogProxy2<D : Dialog, P : Any> : BaseWakeBefDestroyLifecycleObserver() {
    protected var _dialog: D? = null

    override fun onDestroy(owner: LifecycleOwner) {
        dismissDialog()
        _dialog = null
        super.onDestroy(owner)
    }

    fun dismissDialog() {
        _dialog?.dismiss()
    }

    @CallSuper
    open fun showDialog(activity: Activity, params: P) {
        if(activity is LifecycleOwner){
            bindLifecycleIfNeed(activity as LifecycleOwner)
        }
    }

    @CallSuper
    open fun showDialog(activity: Activity, lifecycleOwner: LifecycleOwner, params: P) {
        bindLifecycleIfNeed(lifecycleOwner)
    }

    fun bindLifecycleIfNeed(lifecycleOwner: LifecycleOwner){
        if (lifecycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED){
            return
        }

        /*
         * 如果绑定的不是当前 Activity：
         *
         * 1. 父类 bindLifecycle() 会解除旧 Lifecycle。
         * 2. 当前 Dialog 持有旧 Activity Context，不能继续复用。
         */
        if (!isLifecycleBound(lifecycleOwner)) {
            dismissDialog()
            _dialog = null
        }

        bindLifecycle(lifecycleOwner)
    }

//    fun showDialog(activity: Activity, title: String, content: String) {
//        if (_dialogTxtHtml == null)
//            _dialogTxtHtml = DialogTxtHtml(activity, title, content)
//        else {
//            if (_dialogTxtHtml!!.isShowing && UtilKActivityWrapper.getFloatWindowSize(activity) > 2)
//                _dialogTxtHtml!!.dismiss()
//            _dialogTxtHtml!!.setTitle(title)
//            _dialogTxtHtml!!.setContent(content)
//        }
//        _dialogTxtHtml!!.show()
//    }
}