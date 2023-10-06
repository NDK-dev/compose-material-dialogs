package com.vanpra.composematerialdialogs

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberDialogState

private fun DpSize.toScreenConfiguration(): ScreenConfiguration {
    return ScreenConfiguration(
        width.value.toInt(),
        height.value.toInt()
    )
}

@Composable
internal actual fun rememberScreenConfiguration(): ScreenConfiguration {
    return LocalScreenConfiguration.current
}

@Composable
internal actual fun isSmallDevice(): Boolean {
    return false
}

@Composable
internal actual fun isLargeDevice(): Boolean {
    return true
}

internal val LocalScreenConfiguration = compositionLocalOf<ScreenConfiguration>{ throw IllegalStateException("Unused") }

@Composable
internal actual fun DialogBox(
    onDismissRequest: () -> Unit,
    properties: MaterialDialogProperties,
    content: @Composable () -> Unit
) = Dialog(
    onCloseRequest = onDismissRequest,
    state = rememberDialogState(position = properties.position.toWindowPosition(), size = properties.size),
    title = properties.title,
    icon = properties.icon,
    resizable = properties.resizable
) {
    BoxWithConstraints {
        CompositionLocalProvider(
            LocalScreenConfiguration provides ScreenConfiguration(
                maxWidth.value.toInt(),
                maxHeight.value.toInt()
            ),
            content = content
        )
    }
}

private fun DesktopWindowPosition.toWindowPosition(): WindowPosition {
    return when (this) {
        DesktopWindowPosition.PlatformDefault -> WindowPosition.PlatformDefault
        is DesktopWindowPosition.Absolute -> WindowPosition(x = x, y = y)
        is DesktopWindowPosition.Aligned -> WindowPosition(alignment)
    }
}

@Composable
internal actual fun getDialogShape(shape: Shape) = RectangleShape

@Composable
internal actual fun ScreenConfiguration.getMaxHeight(): Dp {
    return screenHeightDp.dp
}

@Composable
internal actual fun ScreenConfiguration.getPadding(maxWidth: Dp): Dp {
    return 0.dp
}

internal actual fun Modifier.dialogHeight(): Modifier = fillMaxHeight()

internal actual fun Modifier.dialogMaxSize(maxHeight: Dp): Modifier = this

internal actual fun getLayoutHeight(maxHeightPx: Int, layoutHeight: Int): Int {
    return maxHeightPx
}
