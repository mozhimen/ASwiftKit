package com.mozhimen.uik.databinding.bases.viewdatabinding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import com.mozhimen.kotlin.elemk.androidx.appcompat.commons.IActivity
import com.mozhimen.kotlin.elemk.androidx.appcompat.commons.IFragment
import com.mozhimen.kotlin.elemk.androidx.fragment.bases.BaseDialogFragment

open class BaseDialogFragmentVDB<VDB : ViewDataBinding> : BaseDialogFragment, IActivity, IFragment {
    /**
     * 针对Hilt(@JvmOverloads kotlin默认参数值无效)
     * @constructor
     */
    constructor() : super()

    //////////////////////////////////////////////////////////////////////////////

    private var _vdb: VDB? = null
    protected val vdb get() = _vdb!!

    //////////////////////////////////////////////////////////////////////////////

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflateView(container)
        _vdb = com.mozhimen.uik.databinding.utils.ViewDataBindingUtil.get_ofClass<VDB>(this::class.java, inflater, container/*, 0*/).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return vdb.root
    }

    /**
     * 及时释放vb避免内存泄漏
     */
    @CallSuper
    override fun onDestroyView() {
        vdb.unbind()
        _vdb = null
        super.onDestroyView()
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initLayout()
            initData(savedInstanceState)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initLayout() {

    }

    @CallSuper
    override fun initData(savedInstanceState: Bundle?) {
        initView(savedInstanceState)
        initObserver()
        initEvent()
    }
}