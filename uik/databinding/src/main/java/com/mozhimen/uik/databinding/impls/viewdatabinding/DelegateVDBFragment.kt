package com.mozhimen.uik.databinding.impls.viewdatabinding

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.uik.databinding.bases.viewdatabinding.BaseDelegateVDB

/**
 * @ClassName FragmentViewBindingDelegate
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/9/29 0:43
 * @Version 1.0
 */
class DelegateVDBFragment<VDB : ViewDataBinding>(
    clazz: Class<VDB>,
    fragment: Fragment
) : com.mozhimen.uik.databinding.bases.viewdatabinding.BaseDelegateVDB<Fragment, VDB>(clazz, fragment){

    override fun getViewDataBindingLifecycleOwner(): LifecycleOwner? {
        return _obj?.viewLifecycleOwner
    }
}