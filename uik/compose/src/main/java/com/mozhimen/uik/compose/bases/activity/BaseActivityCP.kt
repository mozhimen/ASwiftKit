package com.mozhimen.uik.compose.bases.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import com.mozhimen.kotlin.elemk.androidx.appcompat.bases.BaseActivity
import com.mozhimen.kotlin.elemk.androidx.appcompat.commons.IActivity
import com.mozhimen.kotlin.elemk.commons.I_Listener

/**
 * @ClassName BaseActivityCP
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2025/2/2 16:51
 * @Version 1.0
 */
abstract class BaseActivityCP : BaseActivity(), IActivity {
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            initFlag()
            initLayout()
            initData(savedInstanceState)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initLayout() {
        setContent(getCompositionContext(), getContent())
    }

    @CallSuper
    override fun initData(savedInstanceState: Bundle?) {
        initView(savedInstanceState)
        initObserver()
    }

    ///////////////////////////////////////////////////////////////

    abstract fun getContent(): @Composable I_Listener

    ///////////////////////////////////////////////////////////////

    open fun getCompositionContext(): CompositionContext? = null
}