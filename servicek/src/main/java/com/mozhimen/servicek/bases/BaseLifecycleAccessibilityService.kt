package com.mozhimen.servicek.bases

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.kotlin.lintk.optins.manifest.application.service.OService_ACCESSIBILITY

/**
 * @ClassName BaseLifecycleService
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/6/10 18:57
 * @Version 1.0
 */
@OService_ACCESSIBILITY
abstract class BaseLifecycleAccessibilityService : AccessibilityService(), LifecycleOwner {

    private val _dispatcher = AccessibilityServiceLifecycleDispatcher(this)

    @CallSuper
    override fun onCreate() {
        _dispatcher.onServicePreSuperOnCreate()
        super.onCreate()
    }

    @Deprecated("Deprecated in Java")
    @CallSuper
    override fun onStart(intent: Intent?, startId: Int) {
        _dispatcher.onServicePreSuperOnStart()
        super.onStart(intent, startId)
    }

    // this method is added only to annotate it with @CallSuper.
    // In usual service super.onStartCommand is no-op, but in LifecycleService
    // it results in mDispatcher.onServicePreSuperOnStart() call, because
    // super.onStartCommand calls onStart().
    @CallSuper
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    @CallSuper
    override fun onDestroy() {
        _dispatcher.onServicePreSuperOnDestroy()
        super.onDestroy()
    }

    @CallSuper
    override fun onServiceConnected() {
        _dispatcher.onServicePreSuperOnServiceConnected()
        super.onServiceConnected()
    }

    @CallSuper
    override fun onInterrupt() {
        _dispatcher.onServicePreSuperOnInterrupt()
    }

    override val lifecycle: Lifecycle
        get() = _dispatcher.lifecycle
}
/*
open class BaseLifecycleService2 : Service(), LifecycleOwner {

    private var _lifecycleRegistry: LifecycleRegistry? = null
    protected val lifecycleRegistry: LifecycleRegistry
        get() = _lifecycleRegistry ?: LifecycleRegistry(this).also {
            _lifecycleRegistry = it
        }

    //////////////////////////////////////////////////////////////////////////

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        lifecycleRegistry.handleLifecycleEventOnCreate()
    }

    @CallSuper
    override fun onBind(intent: Intent): IBinder? {
        lifecycleRegistry.handleLifecycleEventOnStart()
        return null
    }

    @CallSuper
    override fun onDestroy() {
        lifecycleRegistry.handleLifecycleEventOnDestroy()
        super.onDestroy()
    }

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry
}
 */