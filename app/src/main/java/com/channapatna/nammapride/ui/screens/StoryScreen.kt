package com.channapatna.nammapride.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.channapatna.nammapride.R
import com.channapatna.nammapride.ui.components.AppTopBar
import com.channapatna.nammapride.ui.theme.*

private data class StoryItem(val title: String, val body: String, val imageRes: Int, val date: String)

private val storyItems = listOf(
    StoryItem(
        "Persian Origins (1780s)",
        "The legacy began over 200 years ago when Tipu Sultan, the ruler of Mysore, invited Persian artisans to train local craftsmen in the art of lacquerware. This unique fusion of Persian skill and Indian wood created a world-renowned craft.",
        R.drawable.story_town,
        "The Beginning"
    ),
    StoryItem(
        "The Sacred Ivory Wood",
        "At the heart of every toy is 'Aale Mara' (Ivory Wood). It is chosen for its smooth grain and light color, which perfectly absorbs the organic lacquer. Today, artisans also use sustainable rubberwood to protect our local forests.",
        R.drawable.story_craft,
        "The Material"
    ),
    StoryItem(
        "Liquid Gold: Lacquer",
        "Natural lacquer is a secret recipe made from insect resin and vegetable dyes. When applied to wood spinning at high speeds, the heat melts the lacquer, creating the signature high-gloss mirror finish that never fades.",
        R.drawable.story_gi,
        "The Technique"
    ),
    StoryItem(
        "Gombegala Ooru",
        "Channapatna earned the title 'Town of Toys' (Gombegala Ooru) as entire streets were transformed into humming workshops. Every house was a factory, and every family member was a guardian of the craft.",
        R.drawable.story_challenge,
        "The Identity"
    ),
    StoryItem(
        "Preserving the Pride",
        "In a world of plastic and machines, Channapatna Namma Pride is our digital shield. By verifying every toy, we ensure that the 200-year-old sweat of our master artisans is recognized and rewarded.",
        R.drawable.story_support,
        "The Mission"
    ),
)

private fun storyColor(imageRes: Int): Color = when (imageRes) {
    R.drawable.story_town      -> Color(0xFF2E7D32)
    R.drawable.story_craft     -> Color(0xFF5D4037)
    R.drawable.story_gi        -> Color(0xFF1565C0)
    R.drawable.story_challenge -> Color(0xFFC62828)
    R.drawable.story_support   -> Color(0xFF6A1B9A)
    else                       -> Color(0xFF37474F)
}

private fun storyIcon(imageRes: Int): ImageVector = when (imageRes) {
    R.drawable.story_town      -> Icons.Default.LocationCity
    R.drawable.story_craft     -> Icons.Default.Build
    R.drawable.story_gi        -> Icons.Default.VerifiedUser
    R.drawable.story_challenge -> Icons.Default.Warning
    R.drawable.story_support   -> Icons.Default.People
    else                       -> Icons.Default.Image
}

@Composable
fun StoryScreen(onBack: () -> Unit) {
    val scrollState = rememberScrollState()
    
    Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(Modifier.fillMaxSize()) {
            AppTopBar("The Story", onBack)
            
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(Spacing.lg),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Header Intro
                Column(verticalArrangement = Arrangement.spacedBy(Spacing.xs)) {
                    Text(
                        "A 200-Year Journey",
                        style = MaterialTheme.typography.headlineMedium,
                        fontFamily = FrauncesFontFamily,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        "From the courts of Tipu Sultan to the palm of your hand.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                storyItems.forEach { item ->
                    StoryCard(item, scrollState)
                }
                
                Spacer(Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun StoryCard(item: StoryItem, scrollState: ScrollState) {
    Surface(
        shape  = RoundedCornerShape(28.dp),
        color  = MaterialTheme.colorScheme.surface,
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)),
        shadowElevation = 2.dp
    ) {
        Column {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(storyColor(item.imageRes))
            ) {
                val painter = rememberAsyncImagePainter(
                    model = item.imageRes,
                    error = painterResource(android.R.drawable.ic_menu_gallery)
                )
                
                if (painter.state is AsyncImagePainter.State.Success) {
                    Image(
                        painter            = painter,
                        contentDescription = item.title,
                        modifier           = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                // Subtle parallax based on scroll
                                val offset = scrollState.value.toFloat()
                                translationY = (offset * 0.05f) // Reduced parallax
                            },
                        contentScale = ContentScale.Crop
                    )
                } else {
                    // Coloured placeholder with icon
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            storyIcon(item.imageRes),
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.4f),
                            modifier = Modifier.size(64.dp)
                        )
                    }
                }
                
                // Date/Tag Badge
                Surface(
                    modifier = Modifier.align(Alignment.TopStart).padding(Spacing.md),
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)
                ) {
                    Text(
                        item.date,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Gradient Overlay
                Box(
                    Modifier.fillMaxSize().background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.4f)),
                            startY = 300f
                        )
                    )
                )
            }
            
            Column(Modifier.padding(Spacing.lg), verticalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                Text(
                    item.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    item.body,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
