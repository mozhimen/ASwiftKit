package com.mozhimen.uik.compose.bases.activity

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.mozhimen.kotlin.utilk.androidx.lifecycle.UtilKViewModel
import com.mozhimen.uik.compose.commons.IComposeVM

/**
 * @ClassName BaseSaveStateActivityCPVM
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/4/17
 * @Version 1.0
 */
abstract class BaseSaveStateActivityCPVM<VM : ViewModel> : BaseSaveStateActivityCP,
    IComposeVM {
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