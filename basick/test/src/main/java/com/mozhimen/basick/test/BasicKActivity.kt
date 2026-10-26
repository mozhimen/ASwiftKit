package com.mozhimen.basick.test

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StyleRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.mozhimen.basick.impls.proxys.DialogProxy
import com.mozhimen.basick.impls.proxys.DialogProxy2
import com.mozhimen.basick.test.databinding.ActivityBasickBinding
import com.mozhimen.basick.test.databinding.LayoutTxtBinding
import com.mozhimen.kotlin.lintk.optins.api.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.api.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.lintk.optins.api.OApiInit_ByLazy
import com.mozhimen.kotlin.lintk.optins.api.OApiInit_InApplication
import com.mozhimen.kotlin.utilk.android.app.UtilKActivityWrapper
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.kotlin.utilk.kotlin.ifNotNullOrEmptyOr
import com.mozhimen.kotlin.utilk.kotlin.strClazz2clazz
import com.mozhimen.kotlin.utilk.wrapper.UtilKScreen
import com.mozhimen.stackk.callback.StackKCb
import com.mozhimen.uik.databinding.bases.viewdatabinding.activity.BaseActivityVDB
import com.mozhimen.uik.databinding.utils.ViewDataBindingUtil
import com.mozhimen.xmlk.R
import com.mozhimen.xmlk.dialogk.bases.commons.IDialogKClickListener
import com.mozhimen.xmlk.dialogk.databinding.bases.BaseDialogKVDB
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BasicKActivity : BaseActivityVDB<ActivityBasickBinding>() {
    @OptIn(OApiInit_ByLazy::class)
    val dialogTxtProxy: DialogTxtProxy by lazy { DialogTxtProxy() }

    @OptIn(OApiInit_InApplication::class)
    override fun initLayout() {
        super.initLayout()

        val canonicalName = this.javaClass.canonicalName
        Log.d(TAG, "handleOnBackPressed: getStackCount ${StackKCb.instance.getStackCount()} javaClass $canonicalName")
        Util.onBackPressed(this) {
            val mainActivityClassName = "com.mozhimen.basick.test.BasicKActivity"

            Log.d(TAG, "handleOnBackPressed: getStackCount ${StackKCb.instance.getStackCount()} javaClass $canonicalName")
            if (StackKCb.instance.getStackCount() == 1 && canonicalName != mainActivityClassName) {
                startContext(mainActivityClassName.strClazz2clazz())
            }
        }
    }

    fun goBackPressed(view: View){
        startContext<BackPressedActivity>()
    }

    @OptIn(OApiInit_ByLazy::class)
    fun showDialog(view: View){
        dialogTxtProxy.showDialog(this,BundleDialogTxt("这是一个研究EdgeToEdge的示例"))
    }

    //

    abstract class BaseMBDialogVDB<VDB : ViewDataBinding>
    constructor(
        context: Context,
        @StyleRes intResTheme: Int = com.mozhimen.xmlk.R.style.ThemeK_Dialog_Blur,
    ) : BaseDialogKVDB<VDB>(context, intResTheme) {
        override fun getDialogWindowWidth(): Int {
            return (UtilKScreen.getWidth_ofDisplayMetrics_ofSys().toFloat() * 8f / 9f).toInt()
        }

        override fun getDialogWindowAnimations(): Int {
            return com.mozhimen.animk.R.style.AnimK_Theme_Scale_Center
        }
    }

    class DialogTxt constructor(
        context: Context,
        private var _content: String,
    ) :
        BaseMBDialogVDB<LayoutTxtBinding>(context) {

        ////////////////////////////////////////////////////////////////////////////////

        init {
            setCancelable(true)
            setCanceledOnTouchOutside(true)
            setDialogClickListener(object : IDialogKClickListener {
                override fun onClickNegative(view: View?, dialog: Dialog) {
                    this@DialogTxt.dismiss()
                }
            })
        }

        ////////////////////////////////////////////////////////////////////////////////

        override fun onViewCreated(view: View) {
            setContent(_content)
            vdb.btnClose.setOnClickListener { getDialogClickListener()?.onClickNegative(it, this) }
        }

        ////////////////////////////////////////////////////////////////////////////////

        fun setContent(content: String) {
            content.ifNotNullOrEmptyOr(onIf = {
                vdb.description.setText(it.also { _content = it })
            })
        }
    }

    @OApiInit_ByLazy
    class DialogTxtProxy : DialogProxy2<DialogTxt, BundleDialogTxt>() {
        override fun showDialog(activity: Activity, params: BundleDialogTxt) {
            super.showDialog(activity, params)
            if (_dialog == null)
                _dialog = DialogTxt(activity, params.content)
            else {
                if (_dialog!!.isShowing && UtilKActivityWrapper.getFloatWindowSize(activity) > 2)
                    _dialog!!.dismiss()
                _dialog!!.setContent(params.content)
            }
            _dialog!!.show()
        }
    }

    data class BundleDialogTxt(
        val content: String,
    )
}