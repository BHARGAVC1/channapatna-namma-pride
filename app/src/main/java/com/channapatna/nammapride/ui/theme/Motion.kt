package com.channapatna.nammapride.ui.theme

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring

object MotionTokens {
    const val Fast = 180
    const val Medium = 320
    const val Slow = 550

    val StandardEasing = FastOutSlowInEasing

    fun <T> smoothSpring() = spring<T>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    )

    fun <T> gentleSpring() = spring<T>(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessVeryLow
    )
}
