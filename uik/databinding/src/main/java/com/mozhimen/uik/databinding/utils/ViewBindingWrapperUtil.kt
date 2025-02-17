package com.mozhimen.uik.databinding.utils

import android.app.Dialog
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

/**
 * @ClassName UtilKViewDataBindingWrapper
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/9/28 23:18
 * @Version 1.0
 */
inline fun <reified VB : ViewBinding> ComponentActivity.viewBinding(): com.mozhimen.uik.databinding.impls.viewbinding.DelegateVBActivity<ComponentActivity, VB> =
    ViewBindingWrapperUtil.viewBinding<VB>(this)

inline fun <reified VB : ViewBinding> Fragment.viewBinding(): com.mozhimen.uik.databinding.impls.viewbinding.DelegateVBFragment<VB> =
    ViewBindingWrapperUtil.viewBinding<VB>(this)

inline fun <D, reified VB : ViewBinding> D.viewBinding(): com.mozhimen.uik.databinding.impls.viewbinding.DelegateVBDialog<D, VB> where  D : Dialog, D : LifecycleOwner =
    ViewBindingWrapperUtil.viewBinding<D, VB>(this)

inline fun <reified VB : ViewBinding> LifecycleOwner.viewBinding(): com.mozhimen.uik.databinding.impls.viewbinding.DelegateVBAny<VB> =
    ViewBindingWrapperUtil.viewBinding(this)

//////////////////////////////////////////////////////////////////

object ViewBindingWrapperUtil {
    inline fun <reified VB : ViewBinding> viewBinding(componentActivity: ComponentActivity): com.mozhimen.uik.databinding.impls.viewbinding.DelegateVBActivity<ComponentActivity, VB> =
        com.mozhimen.uik.databinding.impls.viewbinding.DelegateVBActivity(VB::class.java, componentActivity)

    inline fun <reified VB : ViewBinding> viewBinding(fragment: Fragment): com.mozhimen.uik.databinding.impls.viewbinding.DelegateVBFragment<VB> =
        com.mozhimen.uik.databinding.impls.viewbinding.DelegateVBFragment(VB::class.java, fragment)

    inline fun <D, reified VB : ViewBinding> viewBinding(dialog: D): com.mozhimen.uik.databinding.impls.viewbinding.DelegateVBDialog<D, VB> where D : Dialog, D : LifecycleOwner =
        com.mozhimen.uik.databinding.impls.viewbinding.DelegateVBDialog(VB::class.java, dialog)

    inline fun <reified VB : ViewBinding> viewBinding(obj: LifecycleOwner): com.mozhimen.uik.databinding.impls.viewbinding.DelegateVBAny<VB> =
        com.mozhimen.uik.databinding.impls.viewbinding.DelegateVBAny(VB::class.java, obj)
}