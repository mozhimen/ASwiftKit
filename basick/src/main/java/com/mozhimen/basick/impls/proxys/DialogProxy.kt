package com.mozhimen.basick.impls.proxys

import android.app.Activity
import android.app.Dialog
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.basick.bases.BaseWakeBefDestroyLifecycleObserver
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.kotlin.utilk.android.app.UtilKActivityWrapper

/**
 * @ClassName DialogProxy
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/4/27
 * @Version 1.0
 */
@OApiInit_ByLazy
@OApiCall_BindLifecycle
@OApiCall_BindViewLifecycle
abstract class DialogProxy<D : Dialog, P : Any> : BaseWakeBefDestroyLifecycleObserver() {
    protected var _dialog: D? = null

    override fun onDestroy(owner: LifecycleOwner) {
        dismissDialog()
        _dialog = null
        super.onDestroy(owner)
    }

    fun dismissDialog() {
        _dialog?.dismiss()
    }

    abstract fun showDialog(activity: Activity, params: P)

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