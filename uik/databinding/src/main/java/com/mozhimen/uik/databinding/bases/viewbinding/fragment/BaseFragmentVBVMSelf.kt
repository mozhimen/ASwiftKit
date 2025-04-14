package com.mozhimen.uik.databinding.bases.viewbinding.fragment

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
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
 * 这里的VM是和Activity共享的VM,私有可以通过代理的方式引入
 *
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/2/27 13:02
 * @Version 1.0
 */
abstract class BaseFragmentVBVMSelf<VB : ViewBinding, VM : BaseViewModel> : BaseFragmentVB<VB>,
    IViewBindingVM<VB> {

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
    }
}