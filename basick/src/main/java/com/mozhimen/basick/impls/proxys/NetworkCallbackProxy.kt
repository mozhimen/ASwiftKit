package com.mozhimen.basick.impls.proxys

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.basick.bases.BaseWakeBefDestroyLifecycleObserver
import com.mozhimen.kotlin.elemk.android.os.cons.CVersCode
import com.mozhimen.kotlin.lintk.optins.api.OApiInit_ByLazy
import com.mozhimen.kotlin.lintk.optins.api.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.manifest.uses_permission.OUsesPermission_ACCESS_NETWORK_STATE
import com.mozhimen.kotlin.elemk.android.cons.CPermission
import com.mozhimen.kotlin.lintk.optins.api.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.utilk.android.app.UtilKApplicationWrapper
import com.mozhimen.kotlin.utilk.android.net.UtilKConnectivityManager
import com.mozhimen.kotlin.utilk.android.os.UtilKBuildVersion
import com.mozhimen.kotlin.utilk.kotlin.UtilKLazyJVM.lazy_ofNone

/**
 * @ClassName SenseKNetConnSimpleProxy
 * @Description TODO
 * @Author Mozhimen & Kolin
 * @Version 1.0
 */
@OApiCall_BindViewLifecycle
@OUsesPermission_ACCESS_NETWORK_STATE
@OApiCall_BindLifecycle
@OApiInit_ByLazy
@RequiresApi(CVersCode.V_21_5_L)
class NetworkCallbackProxy : BaseWakeBefDestroyLifecycleObserver {
    protected val _context by lazy_ofNone { UtilKApplicationWrapper.instance.applicationContext }

    private val _networkCallback: ConnectivityManager.NetworkCallback

    @RequiresPermission(CPermission.ACCESS_NETWORK_STATE)
    constructor(networkCallback: ConnectivityManager.NetworkCallback) : super() {
        _networkCallback = networkCallback
        if (UtilKBuildVersion.isAfterV_24_7_N())
            UtilKConnectivityManager.registerDefaultNetworkCallback(_context, _networkCallback)
        else
            UtilKConnectivityManager.registerNetworkCallback(NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build(), _context, _networkCallback)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        UtilKConnectivityManager.unregisterNetworkCallback(_context, _networkCallback)
        super.onDestroy(owner)
    }
}