package com.mozhimen.basick.test

import android.app.Application
import com.mozhimen.kotlin.lintk.optins.api.OApiInit_InApplication
import com.mozhimen.kotlin.lintk.optins.api.OApiMultiDex_InApplication
import com.mozhimen.stackk.bases.BaseApplication
import com.mozhimen.stackk.callback.StackKCb

@OptIn(OApiMultiDex_InApplication::class)
class BasicKApplication: BaseApplication() {
}