package com.mozhimen.uik.databinding.impls.viewbinding

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.mozhimen.uik.databinding.bases.viewbinding.BaseDelegateVB

/**
 * @ClassName ActivityDataBindingDelegate
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/9/29 0:32
 * @Version 1.0
 */
class DelegateVBActivity<A,VB : ViewBinding>(
    clazz: Class<VB>,
    activity: A
) : com.mozhimen.uik.databinding.bases.viewbinding.BaseDelegateVB<A, VB>(clazz, activity) where A : Activity, A : LifecycleOwner