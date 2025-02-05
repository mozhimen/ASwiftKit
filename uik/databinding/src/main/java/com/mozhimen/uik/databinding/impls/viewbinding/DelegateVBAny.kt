package com.mozhimen.uik.databinding.impls.viewbinding

import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.mozhimen.uik.databinding.bases.viewbinding.BaseDelegateVB

/**
 * @ClassName FragmentViewBindingDelegate
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/9/29 0:43
 * @Version 1.0
 */
class DelegateVBAny<VB : ViewBinding>(
    clazz: Class<VB>,
    obj: LifecycleOwner
) : com.mozhimen.uik.databinding.bases.viewbinding.BaseDelegateVB<LifecycleOwner, VB>(clazz, obj)