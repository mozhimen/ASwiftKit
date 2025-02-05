package com.mozhimen.uik.compose.commons

import androidx.lifecycle.ViewModelProvider

/**
 * @ClassName ICompose
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2025/2/2 16:50
 * @Version 1.0
 */
interface IComposeVM {
    fun getViewModelProviderFactory(): ViewModelProvider.Factory? = null
}