package com.mozhimen.basick.test

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import com.mozhimen.kotlin.elemk.commons.I_Listener

object Util {
    fun onBackPressed(componentActivity: ComponentActivity, onBackPressed: I_Listener){
        componentActivity.onBackPressedDispatcher.addCallback(componentActivity, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed.invoke()

                // 暂时禁用当前回调，再交给下一个回调或系统默认行为。
                // 如果不禁用，会再次进入 handleOnBackPressed()，造成递归。
                isEnabled = false
                try {
                    componentActivity.onBackPressedDispatcher.onBackPressed()
                } finally {
                    isEnabled = true
                }
            }
        })
    }
}