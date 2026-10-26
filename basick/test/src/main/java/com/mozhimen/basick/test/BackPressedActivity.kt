package com.mozhimen.basick.test

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mozhimen.basick.test.databinding.ActivityBackPressedBinding
import com.mozhimen.basick.test.databinding.ActivityBasickBinding
import com.mozhimen.kotlin.lintk.optins.api.OApiInit_InApplication
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.kotlin.utilk.kotlin.strClazz2clazz
import com.mozhimen.stackk.callback.StackKCb
import com.mozhimen.uik.databinding.bases.viewdatabinding.activity.BaseActivityVDB

class BackPressedActivity : BaseActivityVDB<ActivityBackPressedBinding>() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_back_pressed)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//    }

    @OptIn(OApiInit_InApplication::class)
    override fun initLayout() {
        super.initLayout()

        val canonicalName = this.javaClass.canonicalName
        Log.d(TAG, "handleOnBackPressed: getStackCount ${StackKCb.instance.getStackCount()} javaClass $canonicalName")
        Util.onBackPressed(this) {
            val mainActivityClassName = "com.mozhimen.basick.test.BasicKActivity"

            Log.d(TAG, "handleOnBackPressed: getStackCount ${StackKCb.instance.getStackCount()} javaClass $canonicalName")
            if (StackKCb.instance.getStackCount() == 1 && canonicalName != mainActivityClassName) {
                startContext(mainActivityClassName.strClazz2clazz())
            }
        }
    }
}