package com.vanpra.composematerialdialogdemos.desktop.demos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import com.vanpra.composematerialdialogdemos.demos.BasicListDialogDemo
import com.vanpra.composematerialdialogdemos.demos.MultiSelectionDemo
import com.vanpra.composematerialdialogdemos.demos.SingleSelectionDemo

class BasicListDialogDemoScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey
    @Composable
    override fun Content() {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            BasicListDialogDemo()
        }
    }
}

class MultiSelectionDemoScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey
    @Composable
    override fun Content() {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            MultiSelectionDemo()
        }
    }
}

class SingleSelectionDemoScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey
    @Composable
    override fun Content() {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            SingleSelectionDemo()
        }
    }
}


