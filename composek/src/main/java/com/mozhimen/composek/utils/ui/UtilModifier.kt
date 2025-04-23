package com.mozhimen.composek.utils.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import com.mozhimen.kotlin.elemk.commons.I_Listener
import kotlinx.coroutines.delay

/**
 * @ClassName UtilKModifier
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/9/26
 * @Version 1.0
 */
fun Modifier.debouncedClickable(delay: Long = 500, onClick: I_Listener): Modifier =
    UtilModifier.debouncedClickable(this, delay, onClick)

fun Modifier.borderDebug(): Modifier =
    UtilModifier.borderDebug(this)

@Composable
fun Modifier.cancelRipperClick(onClick: I_Listener): Modifier =
    UtilModifier.cancelRipperClick(this, onClick)

//////////////////////////////////////////////////////////////////////////////

object UtilModifier {
    @Composable
    @SuppressLint("ModifierFactoryExtensionFunction")
    fun cancelRipperClick(modifier: Modifier, onClick: I_Listener) =
        modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )

    @JvmStatic
    @SuppressLint("ModifierFactoryUnreferencedReceiver", "ModifierFactoryExtensionFunction")
    fun debouncedClickable(modifier: Modifier, delay: Long = 500, onClick: I_Listener): Modifier =
        modifier.composed {
            //按钮是否可点击
            var canClick by remember {
                mutableStateOf(true)
            }
            LaunchedEffect(key1 = canClick, block = {
                if (!canClick) {
                    delay(delay)
                    canClick = true
                }
            })
            Modifier.clickable(canClick) {
                canClick = false
                onClick()
            }
        }

    @JvmStatic
    fun borderDebug(modifier: Modifier): Modifier =
        modifier.border(3.dp, UtilColor.getRandomColor())
}