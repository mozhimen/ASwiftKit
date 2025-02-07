package com.mozhimen.basick.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mozhimen.kotlin.elemk.commons.ISuspendExt_Listener
import com.mozhimen.kotlin.utilk.java.lang.UtilKThreadWrapper
import com.mozhimen.kotlin.utilk.kotlinx.coroutines.UtilKCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @ClassName UtilKLifecycle
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/5/12 14:47
 * @Version 1.0
 */

fun LifecycleOwner.requireContext(): Context =
    UtilKLifecycleOwnerWrapper.requireContext(this)

////////////////////////////////////////////////////////////////////

fun LifecycleOwner.runOnMainScope(block: ISuspendExt_Listener<CoroutineScope>) {
    UtilKLifecycleOwnerWrapper.runOnMainScope(this, block)
}

fun LifecycleOwner.runOnBackScope(block: ISuspendExt_Listener<CoroutineScope>) {
    UtilKLifecycleOwnerWrapper.runOnBackScope(this, block)
}

////////////////////////////////////////////////////////////////////

fun LifecycleOwner.runOnMainThread(block: ISuspendExt_Listener<CoroutineScope>) {
    UtilKLifecycleOwnerWrapper.runOnMainThread(this, block)
}

fun LifecycleOwner.runOnBackThread(block: ISuspendExt_Listener<CoroutineScope>) {
    UtilKLifecycleOwnerWrapper.runOnBackThread(this, block)
}

////////////////////////////////////////////////////////////////////

fun LifecycleOwner.runOnLifecycleState(state: Lifecycle.State, coroutineContext: CoroutineContext = EmptyCoroutineContext, block: ISuspendExt_Listener<CoroutineScope>) {
    UtilKLifecycleOwnerWrapper.runOnLifecycleState(this, state, coroutineContext, block)
}

////////////////////////////////////////////////////////////////////

object UtilKLifecycleOwnerWrapper {
    @JvmStatic
    fun requireContext(lifecycleOwner: LifecycleOwner): Context =
        when (lifecycleOwner) {
            is Activity -> lifecycleOwner
            is Fragment -> lifecycleOwner.requireContext()
            is Dialog -> lifecycleOwner.context
            is PopupWindow -> lifecycleOwner.contentView.context
            else -> throw Exception("hast context")
        }

    ////////////////////////////////////////////////////////////////////

    @JvmStatic
    fun runOnMainScope(lifecycleOwner: LifecycleOwner, block: ISuspendExt_Listener<CoroutineScope>) {
        UtilKCoroutineScope.launchOnMainScope(lifecycleOwner.lifecycleScope, block)
    }

    @JvmStatic
    fun runOnBackScope(lifecycleOwner: LifecycleOwner, block: ISuspendExt_Listener<CoroutineScope>) {
        UtilKCoroutineScope.launchOnBackScope(lifecycleOwner.lifecycleScope, block)
    }

    ////////////////////////////////////////////////////////////////////

    @JvmStatic
    fun runOnMainThread(lifecycleOwner: LifecycleOwner, block: ISuspendExt_Listener<CoroutineScope>) {
        UtilKThreadWrapper.runOnMainThread(lifecycleOwner, block)
    }

    @JvmStatic
    fun runOnBackThread(lifecycleOwner: LifecycleOwner, block: ISuspendExt_Listener<CoroutineScope>) {
        UtilKThreadWrapper.runOnBackThread(lifecycleOwner, block)
    }

    ////////////////////////////////////////////////////////////////////

    @JvmStatic
    fun runOnLifecycleState(
        lifecycleOwner: LifecycleOwner,
        state: Lifecycle.State,
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
        block: ISuspendExt_Listener<CoroutineScope>,
    ) {
        runOnLifecycleState(lifecycleOwner, lifecycleOwner.lifecycleScope, state, coroutineContext, block)
    }

    @JvmStatic
    fun runOnLifecycleState(
        lifecycleOwner: LifecycleOwner,
        coroutineScope: CoroutineScope,
        state: Lifecycle.State,
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
        block: ISuspendExt_Listener<CoroutineScope>,
    ) {
        coroutineScope.launch(coroutineContext) {
            lifecycleOwner.repeatOnLifecycle(state) {
                this.block()
            }
        }
    }
}