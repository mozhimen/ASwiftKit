package com.mozhimen.composek.elems.commons

import androidx.compose.runtime.Composable

/**
 * @ClassName IListener
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/4/23
 * @Version 1.0
 */
typealias ICompose_Listener = @Composable () -> Unit
typealias IComposeA_Listener<A> = @Composable (a:A) -> Unit