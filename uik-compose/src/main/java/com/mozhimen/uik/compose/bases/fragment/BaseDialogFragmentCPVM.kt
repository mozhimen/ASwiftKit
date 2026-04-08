package com.mozhimen.uik.compose.bases.fragment

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.mozhimen.kotlin.utilk.androidx.lifecycle.UtilKViewModel
import com.mozhimen.uik.compose.commons.IComposeVM

/**
 * @ClassName BaseDialogFragmentCPVM
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/4/25
 * @Version 1.0
 */
abstract class BaseDialogFragmentCPVM<VM : ViewModel> : BaseDialogFragmentCP, IComposeVM {
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