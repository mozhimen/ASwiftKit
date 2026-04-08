package com.mozhimen.uik.databinding.bases.viewbinding.fragment

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.mozhimen.kotlin.elemk.androidx.lifecycle.bases.BaseViewModel
import com.mozhimen.kotlin.utilk.androidx.lifecycle.UtilKViewModel
import com.mozhimen.uik.databinding.commons.IViewBindingVM
import com.mozhimen.uik.databinding.commons.IViewDataBindingVM

/**
 * @ClassName BaseFragmentVBVM
 * @Description class BaseDemoFragment : BaseFragment<FragmentBasekFragmentBinding, BaseDemoViewModel>() {
 * override fun assignVM() {vdb.vm = vm}
 * override fun initView() {}}
 *
 * 这里的VM1是和Activity共享的VM,私有VM2
 *
 * @Author mozhimen / Kolin Zhao
 * @Version 1.0
 */
abstract class BaseFragmentVBVMVMSelf<VB : ViewBinding, VMSHARE : BaseViewModel, VMSELF : BaseViewModel> : BaseFragmentVB<VB>, IViewBindingVM<VB> {

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

    protected lateinit var vmShare: VMSHARE
    protected lateinit var vmSelf: VMSELF
    protected var _factoryShare: ViewModelProvider.Factory?
    protected var _factorySelf: ViewModelProvider.Factory?

    //////////////////////////////////////////////////////////////////////////////

    @CallSuper
    override fun initLayout() {
        super.initLayout()
        vmShare = UtilKViewModel.get(this.requireActivity(), _factoryShare/*, 1*/)
        vmSelf = UtilKViewModel.get(this, _factorySelf/*, 1*/)
    }
}