package com.kerimbr.compokedex.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kerimbr.compokedex.R


// Set of Material typography styles to start with

val plusJakartaSansFontFamily: FontFamily = FontFamily(
    Font(R.font.pjs_extra_light, FontWeight.Thin),
    Font(R.font.pjs_extra_light_italic, FontWeight.Thin, style = FontStyle.Italic),

    Font(R.font.pjs_light, FontWeight.Light),
    Font(R.font.pjs_light_italic, FontWeight.Light, style = FontStyle.Italic),

    Font(R.font.pjs_regular, FontWeight.Normal),
    Font(R.font.pjs_italic, FontWeight.Normal, style = FontStyle.Italic),

    Font(R.font.pjs_medium, FontWeight.Medium),
    Font(R.font.pjs_medium_italic, FontWeight.Medium, style = FontStyle.Italic),

    Font(R.font.pjs_semi_bold, FontWeight.SemiBold),
    Font(R.font.pjs_semi_bold_italic, FontWeight.SemiBold, style = FontStyle.Italic),

    Font(R.font.pjs_bold, FontWeight.Bold),
    Font(R.font.pjs_bold_italic, FontWeight.Bold, style = FontStyle.Italic),

    Font(R.font.pjs_extra_bold, FontWeight.ExtraBold),
    Font(R.font.pjs_extra_bold_italic, FontWeight.ExtraBold, style = FontStyle.Italic),

    )

val Typography = Typography(

    displayLarge = TextStyle(
        fontFamily = plusJakartaSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),

    displayMedium = TextStyle(
        fontFamily = plusJakartaSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    displaySmall = TextStyle(
        fontFamily = plusJakartaSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    headlineLarge = TextStyle(
        fontFamily = plusJakartaSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = plusJakartaSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = plusJakartaSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = plusJakartaSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = plusJakartaSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    bodySmall = TextStyle(
        fontFamily = plusJakartaSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),

    labelLarge = TextStyle(
        fontFamily = plusJakartaSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    labelMedium = TextStyle(
        fontFamily = plusJakartaSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),

    labelSmall = TextStyle(
        fontFamily = plusJakartaSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),

)