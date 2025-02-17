package com.mozhimen.uik.databinding.bases.viewdatabinding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import com.mozhimen.kotlin.elemk.androidx.appcompat.commons.IActivity
import com.mozhimen.kotlin.elemk.androidx.appcompat.commons.IFragment
import com.mozhimen.kotlin.elemk.androidx.fragment.bases.BaseFragment
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.kotlin.utilk.androidx.fragment.UtilKFragment

open class BaseFragmentVDB<VDB : ViewDataBinding> : BaseFragment(), IActivity, IFragment {

    private var _vdb: VDB? = null
    protected val vdb get() = _vdb!!

    //////////////////////////////////////////////////////////////////////////////

    fun isAlive(): Boolean = UtilKFragment.isAlive(this)

    //////////////////////////////////////////////////////////////////////////////

    //@warn 如果子类可以继承, 这里子类的VB一定要放置在第一个
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

    override fun inflateView(viewGroup: ViewGroup?) {
        UtilKLogWrapper.d(TAG, "onCreateView: ")
    }

    override fun initFlag() {

    }

    override fun initLayout() {

    }

    @CallSuper
    override fun initData(savedInstanceState: Bundle?) {
        initView(savedInstanceState)
        initObserver()
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initObserver() {

    }
}