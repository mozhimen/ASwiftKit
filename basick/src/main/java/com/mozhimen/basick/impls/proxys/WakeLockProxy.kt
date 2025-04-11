package com.mozhimen.basick.impls.proxys

import android.os.PowerManager
import android.os.PowerManager.WakeLock
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.basick.bases.BaseWakeBefDestroyLifecycleObserver
import com.mozhimen.kotlin.elemk.android.cons.CPermission
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.kotlin.utilk.android.os.UtilKPowerManager


/**
 * @ClassName WakeLockProxy
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/3/4
 * @Version 1.0
 */

@OApiCall_BindViewLifecycle
@OApiInit_ByLazy
@OApiCall_BindLifecycle
@Deprecated("")
class WakeLockProxy @RequiresPermission(CPermission.WAKE_LOCK) constructor(private val _tag: String = "tag:CpuKeepRunning") : BaseWakeBefDestroyLifecycleObserver() {

    private var _wakeLock: WakeLock? = null
        get() {
            if (field != null) return field
            val wakeLock = UtilKPowerManager.get(_context).newWakeLock(PowerManager.FULL_WAKE_LOCK, _tag)
            return wakeLock.also { field = it }
        }

    ////////////////////////////////////////////////////////////////////////

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        _wakeLock?.acquire(10 * 60 * 1000L /*10 minutes*/)
    }

    override fun onPause(owner: LifecycleOwner) {
        _wakeLock?.release()
        super.onPause(owner)
    }
}