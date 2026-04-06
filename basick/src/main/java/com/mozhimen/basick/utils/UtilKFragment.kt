package com.mozhimen.basick.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.mozhimen.kotlin.elemk.commons.ISuspendExt_Listener
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @ClassName UtilKFragment
 * @Description TODO
 * @Author mozhimen
 * @Date 2026/4/1
 * @Version 1.0
 */
fun Fragment.runOnViewLifecycleState(state: Lifecycle.State, coroutineContext: CoroutineContext = EmptyCoroutineContext, block: ISuspendExt_Listener<CoroutineScope>) {
    UtilKFragment.runOnViewLifecycleState(this, state, coroutineContext, block)
}

object UtilKFragment {
    @JvmStatic
    fun runOnViewLifecycleState(fragment: Fragment, state: Lifecycle.State, coroutineContext: CoroutineContext = EmptyCoroutineContext, block: ISuspendExt_Listener<CoroutineScope>) {
        UtilKLifecycleOwnerWrapper.runOnLifecycleState(fragment.viewLifecycleOwner, fragment.lifecycleScope, state, coroutineContext, block)
    }
}