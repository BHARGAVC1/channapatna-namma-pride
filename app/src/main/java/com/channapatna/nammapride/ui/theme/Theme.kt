package com.channapatna.nammapride.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.channapatna.nammapride.R

// Premium Spacing System
object Spacing {
    val xs = 4.dp
    val sm = 8.dp
    val md = 16.dp
    val lg = 24.dp
    val xl = 32.dp
    val xxl = 48.dp
}

val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val FrauncesFont = GoogleFont("Fraunces")
val NunitoFont = GoogleFont("Nunito")

val FrauncesFontFamily = FontFamily(
    Font(googleFont = FrauncesFont, fontProvider = fontProvider)
)

val NunitoFontFamily = FontFamily(
    Font(googleFont = NunitoFont, fontProvider = fontProvider)
)

// Premium Palette
val ForestGreen  = Color(0xFF123524)
val Emerald      = Color(0xFF1F5A3D)
val Gold         = Color(0xFFD89B3C)
val SoftGold     = Color(0xFFF2C879)
val Ivory        = Color(0xFFFAF7F2)
val Charcoal     = Color(0xFF1F1F1F)
val WarmGray     = Color(0xFF6B625C)
val PremiumPink  = Color(0xFFFF6B81)

// Semantic colors mapping to the new palette
val White        = Color(0xFFFFFFFF)
val OffWhite     = Ivory
val Surface      = Ivory
val BorderLight  = Color(0xFFE5E2DC)
val TextPrimary  = Charcoal
val TextSecond   = WarmGray
val TextHint     = Color(0xFFADABA6)
val AccentGreen  = Emerald
val AccentGreenL = Color(0xFFE8F5EE)
val AccentRed    = Color(0xFFC62828)
val AccentRedL   = Color(0xFFFDECEC)
val GoldAccent   = Gold

// Dark palette
val DarkBg          = Color(0xFF111613)
val DarkSurface     = Color(0xFF1A1F1C)
val DarkSurfaceVar  = Color(0xFF222922)
val DarkBorder      = Color(0xFF2E3830)
val DarkTextPrimary = Color(0xFFE8EDE9)
val DarkTextSecond  = Color(0xFF8FA892)
val DarkTextHint    = Color(0xFF4F6654)
val DarkGreen       = Color(0xFF4CAF80)
val DarkGreenL      = Color(0xFF1A3326)
val DarkRed         = Color(0xFFEF9A9A)
val DarkRedL        = Color(0xFF2D1515)

// Shimmer base colors
val ShimmerLight = Color(0xFFE8E4DE)
val ShimmerDark  = Color(0xFF252B26)

private val LightColorScheme = lightColorScheme(
    primary             = Emerald,
    onPrimary           = White,
    primaryContainer    = Color(0xFFE8F5EE),
    onPrimaryContainer  = Emerald,
    secondary           = Gold,
    onSecondary         = White,
    tertiary            = PremiumPink,
    background          = Ivory,
    surface             = Ivory,
    surfaceVariant      = White,
    onBackground        = Charcoal,
    onSurface           = Charcoal,
    onSurfaceVariant    = WarmGray,
    outline             = BorderLight,
    error               = AccentRed,
    errorContainer      = AccentRedL,
    onError             = White,
    onErrorContainer    = AccentRed
)

private val DarkColorScheme = darkColorScheme(
    primary             = DarkGreen,
    onPrimary           = Color(0xFF003825),
    primaryContainer    = DarkGreenL,
    onPrimaryContainer  = DarkGreen,
    secondary           = DarkTextSecond,
    background          = DarkBg,
    surface             = DarkSurface,
    surfaceVariant      = DarkSurfaceVar,
    onBackground        = DarkTextPrimary,
    onSurface           = DarkTextPrimary,
    onSurfaceVariant    = DarkTextSecond,
    outline             = DarkBorder,
    error               = DarkRed,
    errorContainer      = DarkRedL,
    onError             = Color(0xFF1A0000),
    onErrorContainer    = DarkRed
)

val AppTypography = Typography(
    headlineLarge  = TextStyle(
        fontFamily = FrauncesFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = (-0.5).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FrauncesFontFamily,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold
    ),
    headlineSmall  = TextStyle(
        fontFamily = FrauncesFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    ),
    titleLarge     = TextStyle(
        fontFamily = NunitoFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleMedium    = TextStyle(
        fontFamily = NunitoFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    titleSmall     = TextStyle(
        fontFamily = NunitoFontFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyLarge      = TextStyle(
        fontFamily = NunitoFontFamily,
        fontSize = 15.sp,
        lineHeight = 23.sp
    ),
    bodyMedium     = TextStyle(
        fontFamily = NunitoFontFamily,
        fontSize = 14.sp,
        lineHeight = 21.sp
    ),
    bodySmall      = TextStyle(
        fontFamily = NunitoFontFamily,
        fontSize = 13.sp,
        lineHeight = 19.sp
    ),
    labelLarge     = TextStyle(
        fontFamily = NunitoFontFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium
    ),
    labelSmall     = TextStyle(
        fontFamily = NunitoFontFamily,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.5.sp
    )
)

@Composable
fun ChannapatnaNammaPrideTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography  = AppTypography,
        content     = content
    )
}
