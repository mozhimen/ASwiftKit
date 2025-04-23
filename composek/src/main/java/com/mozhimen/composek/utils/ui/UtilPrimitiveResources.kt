package com.mozhimen.composek.utils.ui

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * @ClassName UtilPrimitiveResources
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/4/18
 * @Version 1.0
 */
@Composable
@ReadOnlyComposable
fun dimensionResourceSp(@DimenRes id: Int): TextUnit =
    dimensionResource(id = id).value.sp