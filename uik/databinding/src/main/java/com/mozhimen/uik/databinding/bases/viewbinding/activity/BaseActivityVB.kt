package com.mozhimen.uik.databinding.bases.viewbinding.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.mozhimen.kotlin.elemk.androidx.appcompat.bases.BaseActivity
import com.mozhimen.kotlin.elemk.androidx.appcompat.commons.IActivity
import com.mozhimen.kotlin.utilk.kotlin.UtilKLazyJVM.lazy_ofNone

abstract class BaseActivityVB<VB : ViewBinding> : BaseActivity, IActivity {
    /**
     * 针对Hilt(@JvmOverloads kotlin默认参数值无效)
     * @constructor
     */
    constructor() : super()

    ////////////////////////////////////////////////////////////////////////

    protected val vb: VB by lazy_ofNone {
        com.mozhimen.uik.databinding.utils.ViewBindingUtil.get_ofClass(this::class.java, layoutInflater/*, 0*/)
    }

    ///////////////////////////////////////////////////////////////

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

    @CallSuper
    override fun initLayout() {
        setContentView(vb.root)
    }

    @CallSuper
    override fun initData(savedInstanceState: Bundle?) {
        initView(savedInstanceState)
        initObserver()
        initEvent()
    }
}