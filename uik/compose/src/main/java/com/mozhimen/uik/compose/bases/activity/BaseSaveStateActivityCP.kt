package com.mozhimen.uik.compose.bases.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import com.mozhimen.composek.elems.commons.ICompose_Listener
import com.mozhimen.kotlin.elemk.androidx.appcompat.bases.BaseSaveStateActivity
import com.mozhimen.kotlin.elemk.androidx.appcompat.commons.IActivity
import com.mozhimen.kotlin.elemk.commons.I_Listener

/**
 * @ClassName BaseSaveStateActivityCP
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/4/17
 * @Version 1.0
 */
abstract class BaseSaveStateActivityCP : BaseSaveStateActivity, IActivity {
    /**
     * 针对Hilt(@JvmOverloads kotlin默认参数值无效)
     * @constructor
     */
    constructor() : super()

    //////////////////////////////////////////////////////////////////////////////

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

    ///////////////////////////////////////////////////////////////

    override fun initLayout() {
        setContent(getCompositionContext(), getContent())
    }

    @CallSuper
    override fun initData(savedInstanceState: Bundle?) {
        initView(savedInstanceState)
        initObserver()
        initEvent()
    }

    ///////////////////////////////////////////////////////////////

    abstract fun getContent(): ICompose_Listener

    ///////////////////////////////////////////////////////////////

    open fun getCompositionContext(): CompositionContext? = null
}