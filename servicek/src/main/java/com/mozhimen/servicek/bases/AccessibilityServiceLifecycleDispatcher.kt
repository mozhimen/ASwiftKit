package com.mozhimen.servicek.bases

import android.app.Service
import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LifecycleService

/**
 * @ClassName AccessibilityServiceLifecycleDispatcher
 * @Description TODO
 * @Author mozhimen
 * @Date 2026/3/31
 * @Version 1.0
 */
/**
 * Helper class to dispatch lifecycle events for a Service. Use it only if it is impossible
 * to use [LifecycleService].
 *
 * @param provider [LifecycleOwner] for a service, usually it is a service itself
 */
open class AccessibilityServiceLifecycleDispatcher(provider: LifecycleOwner) {

    private val registry: LifecycleRegistry
    private val handler: Handler
    private var lastDispatchRunnable: DispatchRunnable? = null

    init {
        registry = LifecycleRegistry(provider)
        @Suppress("DEPRECATION")
        handler = Handler()
    }

    private fun postDispatchRunnable(event: Lifecycle.Event) {
        lastDispatchRunnable?.run()
        lastDispatchRunnable = DispatchRunnable(registry, event)
        handler.postAtFrontOfQueue(lastDispatchRunnable!!)
    }

    open fun onServicePreSuperOnCreate() {
        postDispatchRunnable(Lifecycle.Event.ON_CREATE)
    }

    open fun onServicePreSuperOnServiceConnected() {
        postDispatchRunnable(Lifecycle.Event.ON_START)
    }

    open fun onServicePreSuperOnStart() {
        postDispatchRunnable(Lifecycle.Event.ON_START)
    }

    open fun onServicePreSuperOnInterrupt() {
        postDispatchRunnable(Lifecycle.Event.ON_STOP)
    }

    open fun onServicePreSuperOnDestroy() {
        postDispatchRunnable(Lifecycle.Event.ON_DESTROY)
    }

    open val lifecycle: Lifecycle
        get() = registry

    internal class DispatchRunnable(
        private val registry: LifecycleRegistry,
        val event: Lifecycle.Event,
    ) : Runnable {
        private var wasExecuted = false

        override fun run() {
            if (!wasExecuted) {
                registry.handleLifecycleEvent(event)
                wasExecuted = true
            }
        }
    }
}