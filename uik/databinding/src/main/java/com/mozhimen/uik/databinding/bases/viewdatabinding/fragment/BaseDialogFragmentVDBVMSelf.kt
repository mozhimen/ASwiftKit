package com.mozhimen.uik.databinding.bases.viewdatabinding.fragment

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import com.mozhimen.kotlin.elemk.androidx.lifecycle.bases.BaseViewModel
import com.mozhimen.kotlin.utilk.androidx.lifecycle.UtilKViewModel


/**
 * @ClassName BaseDialogFragmentVBVM
 * @Description 这里的VM是和Activity共享的VM,私有可以通过代理的方式引入
 * @Author Mozhimen & Kolin Zhao
 * @Version 1.0
 */
abstract class BaseDialogFragmentVDBVMSelf<VB : ViewDataBinding, VM : BaseViewModel> : BaseDialogFragmentVDB<VB>,
    com.mozhimen.uik.databinding.commons.IViewDataBindingVM<VB> {
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
        vm = UtilKViewModel.get(this, getViewModelProviderFactory()/*, 1*/)
        bindViewVM(vdb)
    }
}