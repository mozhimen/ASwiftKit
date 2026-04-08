package com.mozhimen.uik.databinding.commons

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

/**
 * @ClassName IViewDataBinding
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/10/22 23:58
 * @Version 1.0
 */
interface IViewDataBindingVM<VDB : ViewDataBinding> {
    /**
     * vdb.vm=vm
     */
    fun bindViewVM(vdb: VDB)

    fun getViewModelProviderFactory(): ViewModelProvider.Factory? = null
}