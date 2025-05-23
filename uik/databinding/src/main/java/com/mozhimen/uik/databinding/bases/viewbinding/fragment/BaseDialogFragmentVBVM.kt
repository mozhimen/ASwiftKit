package com.mozhimen.uik.databinding.bases.viewbinding.fragment

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.mozhimen.kotlin.elemk.androidx.lifecycle.bases.BaseViewModel
import com.mozhimen.kotlin.utilk.androidx.lifecycle.UtilKViewModel
import com.mozhimen.uik.databinding.commons.IViewBindingVM


/**
 * @ClassName BaseDialogFragmentVBVM
 * @Description 这里的VM是和Activity共享的VM,私有可以通过代理的方式引入
 * @Author Mozhimen & Kolin Zhao
 * @Version 1.0
 */
abstract class BaseDialogFragmentVBVM<VB : ViewBinding, VM : BaseViewModel> : BaseDialogFragmentVB<VB>, IViewBindingVM<VB> {

    /**
     * 针对Hilt(@JvmOverloads kotlin默认参数值无效)
     * @constructor
     */
    constructor() : super()

    //////////////////////////////////////////////////////////////////////////////

    protected lateinit var vm: VM

    //////////////////////////////////////////////////////////////////////////////

    @CallSuper
    override fun initLayout() {
        super.initLayout()
        vm = UtilKViewModel.get(this.requireActivity(), getViewModelProviderFactory()/*, 1*/)
    }
}