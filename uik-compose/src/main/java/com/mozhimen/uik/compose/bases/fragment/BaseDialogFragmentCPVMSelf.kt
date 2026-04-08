package com.mozhimen.uik.compose.bases.fragment

import androidx.annotation.CallSuper
import com.mozhimen.kotlin.elemk.androidx.lifecycle.bases.BaseViewModel
import com.mozhimen.kotlin.utilk.androidx.lifecycle.UtilKViewModel
import com.mozhimen.uik.compose.commons.IComposeVM

/**
 * @ClassName BaseDialogFragmentCPVMSelf
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/4/25
 * @Version 1.0
 */
abstract class BaseDialogFragmentCPVMSelf<VM : BaseViewModel> : BaseDialogFragmentCP, IComposeVM {
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