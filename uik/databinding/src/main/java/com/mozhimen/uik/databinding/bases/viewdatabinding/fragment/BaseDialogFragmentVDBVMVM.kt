package com.mozhimen.uik.databinding.bases.viewdatabinding.fragment

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.mozhimen.kotlin.elemk.androidx.appcompat.commons.IActivity
import com.mozhimen.kotlin.elemk.androidx.lifecycle.bases.BaseViewModel
import com.mozhimen.kotlin.utilk.androidx.lifecycle.UtilKViewModel


/**
 * @ClassName BaseDialogFragmentVBVM
 * @Description 这里的VM1是和Activity共享的VM,私有VM2
 * @Author Mozhimen & Kolin Zhao
 * @Version 1.0
 */
abstract class BaseDialogFragmentVDBVMVM<VB : ViewDataBinding, VM1 : BaseViewModel, VM2 : BaseViewModel> : com.mozhimen.uik.databinding.bases.viewdatabinding.fragment.BaseDialogFragmentVDB<VB>, IActivity,
    com.mozhimen.uik.databinding.commons.IViewDataBindingVM<VB> {

    protected var _factoryShare: ViewModelProvider.Factory?
    protected var _factorySelf: ViewModelProvider.Factory?

    /**
     * 针对Hilt(@JvmOverloads kotlin默认参数值无效)
     * @constructor
     */
    constructor() : this(null)

    constructor(factoryShare: ViewModelProvider.Factory?) : this(factoryShare, null)

    constructor(factoryShare: ViewModelProvider.Factory?, factorySelf: ViewModelProvider.Factory?) : super() {
        _factoryShare = factoryShare
        _factorySelf = factorySelf
    }

    //////////////////////////////////////////////////////////////////////////////

    protected lateinit var vmShare: VM1
    protected lateinit var vmSelf: VM2

    //////////////////////////////////////////////////////////////////////////////

    @CallSuper
    override fun initLayout() {
        super.initLayout()
        vmShare = UtilKViewModel.get(this.requireActivity(), _factoryShare/*, 1*/)
        vmSelf = UtilKViewModel.get(this, _factorySelf/*, 1*/)
        bindViewVM(vdb)
    }
}