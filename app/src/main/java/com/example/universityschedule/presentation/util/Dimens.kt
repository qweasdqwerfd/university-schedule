package com.example.universityschedule.presentation.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    // ----- Отступы (spacing) -----
    val space2 : Dp = 2.dp,
    val space4 : Dp = 4.dp,
    val space6 : Dp = 6.dp,
    val space8 : Dp = 8.dp,
    val space10: Dp =  10.dp,
    val space12: Dp =  12.dp,
    val space14: Dp =  14.dp,
    val space16: Dp =  16.dp,
    val space20: Dp =  20.dp,
    val space24: Dp =  24.dp,
    val space28: Dp =  28.dp,
    val space32: Dp =  32.dp,
    val space36: Dp =  36.dp,
    val space40: Dp =  40.dp,
    val space48: Dp =  48.dp,
    val space56: Dp =  56.dp,
    val space64: Dp =  64.dp,

    // ----- Размеры иконок -----
    val iconSizeExtraSmall: Dp = 12.dp,
    val iconSizeSmall: Dp = 16.dp,
    val iconSizeSmallPlus: Dp = 21.dp,
    val iconSizeMedium: Dp = 24.dp,
    val iconMediumPlus: Dp = 28.dp,
    val iconSizeLarge: Dp = 32.dp,
    val iconSizeLargePlus: Dp = 38.dp,
    val iconSizeExtraLarge: Dp = 48.dp,

    // ----- Высоты компонентов -----
    val buttonHeightSmall: Dp = 36.dp,
    val buttonHeightMedium: Dp = 48.dp,
    val buttonHeightLarge: Dp = 56.dp,

    val textFieldHeight: Dp = 56.dp,
    val topBarHeight: Dp = 64.dp,
    val bottomBarHeight: Dp = 72.dp,
    val fabSize: Dp = 56.dp,

    val heightSmall: Dp = 16.dp,
    val heightSmallPlus: Dp = 20.dp,
    val heightSmallExtend: Dp = 25.dp,
    val heightExtraSmall: Dp = 8.dp,
    val heightExtraSmallPlus: Dp = 14.dp,
    val heightMedium: Dp = 32.dp,
    val heightLarge: Dp = 44.dp,
    val heightLargePlus: Dp = 48.dp,
    val heightExtraLarge: Dp = 64.dp,

    // ----- Аватары -----
    val avatarSizeSmall: Dp = 32.dp,
    val avatarSizeMedium: Dp = 40.dp,
    val avatarSizeLarge: Dp = 56.dp,
    val avatarSizeExtraLarge: Dp = 72.dp,
    val avatarSizeHuge: Dp = 96.dp,

    // ----- Карточки -----
    val cardElevationLow: Dp = 2.dp,
    val cardElevationMedium: Dp = 4.dp,
    val cardElevationHigh: Dp = 8.dp,
    val cardCornerSmall: Dp = 6.dp,
    val cardCornerMedium: Dp = 12.dp,
    val cardCornerLarge: Dp = 16.dp,
    val cardCornerExtraLarge: Dp = 24.dp,

    // ----- Скругления (shapes) -----
    val cornerExtraSmall: Dp = 2.dp,
    val cornerSmall: Dp = 4.dp,
    val cornerMedium: Dp = 8.dp,
    val cornerLarge: Dp = 12.dp,
    val cornerExtraLarge: Dp = 16.dp,
    val cornerHuge: Dp = 24.dp,
    val cornerFull: Dp = 50.dp,   // полностью круглое

    // ----- Elevation (тени) -----
    val elevationLow: Dp = 1.dp,
    val elevationMedium: Dp = 3.dp,
    val elevationHigh: Dp = 6.dp,
    val elevationExtraHigh: Dp = 12.dp,
    val elevationHuge: Dp = 24.dp,

    // ----- Width (ширина) -----
    val widthLarge: Dp = 127.dp,
    val widthMedium: Dp = 30.dp,
    val widthSmallExtra: Dp = 16.dp,
    val widthSmallPlus: Dp = 12.dp,
    val widthSmall: Dp = 8.dp,
    val widthSmallMinus: Dp = 5.dp,
    val widthOne: Dp = 1.dp,

    // ----- Thickness (толщина) -----

    val thicknessExtraSmall: Dp = 4.dp,


    )

val LocalDimens = staticCompositionLocalOf { Dimens() }

val MaterialTheme.dimens: Dimens
    @Composable
    get() = LocalDimens.current