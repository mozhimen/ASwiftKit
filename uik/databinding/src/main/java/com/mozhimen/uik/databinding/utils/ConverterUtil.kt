package com.mozhimen.uik.databinding.utils

import androidx.databinding.InverseMethod

/**
 * @ClassName ConverterUtil
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2025/2/11 20:58
 * @Version 1.0
 */
object ConverterUtil {
    @InverseMethod("toFloat")
    @JvmStatic
    fun toString(value: Float?): String {
        return value?.toString() ?: ""
    }

    @JvmStatic
    fun toFloat(value: String): Float {
        return try {
            value.toFloat()
        } catch (e: NumberFormatException) {
            0f
        }
    }
}