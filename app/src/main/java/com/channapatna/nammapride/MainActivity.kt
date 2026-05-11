package com.channapatna.nammapride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.channapatna.nammapride.navigation.AppNavGraph
import com.channapatna.nammapride.navigation.Routes
import com.channapatna.nammapride.ui.theme.ChannapatnaNammaPrideTheme
import com.channapatna.nammapride.ui.theme.MotionTokens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChannapatnaNammaPrideTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val mainRoutes = listOf(
        Routes.Home.route,
        Routes.Catalog.route,
        Routes.ArtisanList.route,
        Routes.Favorites.route
    )

    val showBottomBar = currentDestination?.route in mainRoutes

    Box(Modifier.fillMaxSize()) {
        // Step 5: Ambient Lighting System (Background Glow)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(300.dp)
                .offset(x = 100.dp, y = (-50).dp)
                .blur(120.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.08f), CircleShape)
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(400.dp)
                .offset(x = (-150).dp, y = 100.dp)
                .blur(150.dp)
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.05f), CircleShape)
        )

        AppNavGraph(navController = navController)

        // Step 3: Floating Glass Navigation
        if (showBottomBar) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
                    .navigationBarsPadding()
            ) {
                Surface(
                    modifier = Modifier
                        .height(72.dp)
                        .shadow(12.dp, RoundedCornerShape(28.dp)),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
                    shape = RoundedCornerShape(28.dp),
                    border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.2f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        NavigationItem(
                            icon = Icons.Outlined.Home,
                            selectedIcon = Icons.Filled.Home,
                            label = "Home",
                            isSelected = currentDestination?.hierarchy?.any { it.route == Routes.Home.route } == true,
                            onClick = {
                                navController.navigate(Routes.Home.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                        NavigationItem(
                            icon = Icons.Outlined.GridView,
                            selectedIcon = Icons.Filled.GridView,
                            label = "Catalog",
                            isSelected = currentDestination?.hierarchy?.any { it.route == Routes.Catalog.route } == true,
                            onClick = {
                                navController.navigate(Routes.Catalog.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                        NavigationItem(
                            icon = Icons.Outlined.People,
                            selectedIcon = Icons.Filled.People,
                            label = "Artisans",
                            isSelected = currentDestination?.hierarchy?.any { it.route == Routes.ArtisanList.route } == true,
                            onClick = {
                                navController.navigate(Routes.ArtisanList.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                        NavigationItem(
                            icon = Icons.Outlined.FavoriteBorder,
                            selectedIcon = Icons.Filled.Favorite,
                            label = "Saved",
                            isSelected = currentDestination?.hierarchy?.any { it.route == Routes.Favorites.route } == true,
                            onClick = {
                                navController.navigate(Routes.Favorites.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.NavigationItem(
    icon: ImageVector,
    selectedIcon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.15f else 1f,
        animationSpec = MotionTokens.smoothSpring(),
        label = "scale"
    )

    Column(
        modifier = Modifier
            .weight(1f)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .blur(8.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), CircleShape)
                )
            }
            Icon(
                imageVector = if (isSelected) selectedIcon else icon,
                contentDescription = label,
                tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                modifier = Modifier.size(24.dp).scale(scale)
            )
        }
        AnimatedVisibility(
            visible = isSelected,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}
