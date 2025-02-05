package com.mozhimen.uik.databinding.commons

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 * @ClassName IViewDataBinding
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/10/22 23:58
 * @Version 1.0
 */
interface IViewBindingVM<VB : ViewBinding> {
    fun getViewModelProviderFactory(): ViewModelProvider.Factory? = null
}