package com.channapatna.nammapride.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.channapatna.nammapride.R
import com.channapatna.nammapride.ui.components.AppTopBar
import com.channapatna.nammapride.ui.theme.*

private data class StoryItem(val title: String, val body: String, val imageRes: Int)

private val storyItems = listOf(
    StoryItem("A Town of Toys",
        "Channapatna, 60 km from Bengaluru, has been producing wooden toys for over 200 years. Known as \"Gombegala Ooru\" (Town of Toys) in Kannada, it was under Hyder Ali's reign that Persian craftsmen first introduced lacquerware to local artisans.",
        R.drawable.story_town),
    StoryItem("The Craft",
        "Artisans use locally sourced ivory wood and rubberwood, turned on traditional lathes and coated with natural lacquer made from plant extracts. Toys are painted with mineral and vegetable pigments — no synthetic chemicals touch the final product.",
        R.drawable.story_craft),
    StoryItem("GI Tag Protection",
        "In 2005, Channapatna toys received a Geographical Indication (GI) tag, recognising them as a product unique to this region. This legal protection helps distinguish genuine handcrafted toys from mass-produced imitations.",
        R.drawable.story_gi),
    StoryItem("The Challenge Today",
        "Despite the GI tag, machine-made lookalikes flood the market. Buyers cannot easily tell the difference. Genuine artisans lose livelihood and recognition. This app puts verification power directly in the buyer's hands.",
        R.drawable.story_challenge),
    StoryItem("Supporting Artisans",
        "Every verified purchase directly supports a registered Channapatna artisan. When you scan a QR code with this app, you're not just buying a toy — you're connecting with a person, their family, and a 200-year-old tradition.",
        R.drawable.story_support),
)

@Composable
fun StoryScreen(onBack: () -> Unit) {
    val scrollState = rememberScrollState()
    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppTopBar("The Story", onBack)
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            storyItems.forEach { item ->
                StoryCard(item, scrollState)
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun StoryCard(item: StoryItem, scrollState: ScrollState) {
    Surface(
        shape  = RoundedCornerShape(16.dp),
        color  = MaterialTheme.colorScheme.surface,
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline),
        shadowElevation = 1.dp
    ) {
        Column {
            // Story illustration
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            ) {
                Image(
                    painter            = painterResource(item.imageRes),
                    contentDescription = item.title,
                    modifier           = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .graphicsLayer {
                            translationY = scrollState.value * 0.15f
                        },
                    contentScale = ContentScale.Crop
                )
            }
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(item.title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                Text(item.body, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
