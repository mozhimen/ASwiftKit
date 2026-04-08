package com.mozhimen.uik.databinding.impls.viewdatabinding

import android.app.Activity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.uik.databinding.bases.viewdatabinding.BaseDelegateVDB

/**
 * @ClassName ActivityDataBindingDelegate
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/9/29 0:32
 * @Version 1.0
 */
class DelegateVDBActivity<A, VDB : ViewDataBinding>(
    clazz: Class<VDB>,
    activity: A
) : com.mozhimen.uik.databinding.bases.viewdatabinding.BaseDelegateVDB<A, VDB>(clazz, activity) where A : Activity, A : LifecycleOwner