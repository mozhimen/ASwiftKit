package com.mozhimen.uik.databinding.bases.viewbinding.activity

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.mozhimen.kotlin.utilk.androidx.lifecycle.UtilKViewModel

/**
 * @ClassName BaseBarActivityVBVM
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Version 1.0
 */
abstract class BaseBarActivityVBVM<VDB : ViewDataBinding, VM : ViewModel> : com.mozhimen.uik.databinding.bases.viewbinding.activity.BaseBarActivityVB<VDB>,
    com.mozhimen.uik.databinding.commons.IViewDataBindingVM<VDB> {

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