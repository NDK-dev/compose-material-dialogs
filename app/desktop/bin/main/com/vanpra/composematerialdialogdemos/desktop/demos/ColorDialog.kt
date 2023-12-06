package com.vanpra.composematerialdialogdemos.desktop.demos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import com.vanpra.composematerialdialogdemos.demos.ColorDialogDemo

class ColorDialogDemoScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey
    @Composable
    override fun Content() {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            ColorDialogDemo()
        }
    }
}
