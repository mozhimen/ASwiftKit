package com.mozhimen.uik.compose.bases.fragment

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mozhimen.kotlin.elemk.androidx.lifecycle.bases.BaseViewModel
import com.mozhimen.kotlin.utilk.androidx.lifecycle.UtilKViewModel
import com.mozhimen.uik.compose.bases.activity.BaseSaveStateActivityCP
import com.mozhimen.uik.compose.commons.IComposeVM

/**
 * @ClassName BaseDialogFragmentCPVMVMSelf
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/4/25
 * @Version 1.0
 */
abstract class BaseDialogFragmentCPVMVMSelf<VMSHARE : BaseViewModel, VMSELF : BaseViewModel> : BaseDialogFragmentCP,
    IComposeVM {
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

    protected var _factoryShare: ViewModelProvider.Factory?
    protected var _factorySelf: ViewModelProvider.Factory?
    protected lateinit var vmShare: VMSHARE
    protected lateinit var vmSelf: VMSELF

    //////////////////////////////////////////////////////////////////////////////

    @CallSuper
    override fun initLayout() {
        super.initLayout()
        vmShare = UtilKViewModel.get(this.requireActivity(), _factoryShare/*, 1*/)
        vmSelf = UtilKViewModel.get(this, _factorySelf/*, 1*/)
    }
}