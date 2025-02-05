package com.mozhimen.uik.compose.test

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mozhimen.kotlin.elemk.commons.I_Listener
import com.mozhimen.uik.compose.bases.activity.BaseBarActivityCP

class MainActivity : BaseBarActivityCP() {

    override fun getContent(): @Composable I_Listener {
        return {
            HomePage()
        }
    }

    @Preview
    @Composable
    fun HomePage() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                "Hello Compose"
            )
        }
    }
}