package com.channapatna.nammapride.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import com.channapatna.nammapride.ui.screens.*

sealed class Routes(val route: String) {
    object Home         : Routes("home")
    object Catalog      : Routes("catalog")
    object ArtisanList  : Routes("artisan_list")
    object Story        : Routes("story")
    object Favorites    : Routes("favorites")
    object Result : Routes("result/{toyId}") {
        fun createRoute(toyId: String) = "result/$toyId"
    }
    object ArtisanProfile : Routes("artisan/{artisanId}") {
        fun createRoute(id: String) = "artisan/$id"
    }
    object ToyDetail : Routes("toy/{toyId}") {
        fun createRoute(toyId: String) = "toy/$toyId"
    }
    object MapScreen : Routes("map/{artisanId}") {
        fun createRoute(id: String) = "map/$id"
    }
}

private val slideEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { it },
        animationSpec  = tween(320, easing = FastOutSlowInEasing)
    ) + fadeIn(tween(220))
}

private val slideExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { -it / 3 },
        animationSpec = tween(320, easing = FastOutSlowInEasing)
    ) + fadeOut(tween(180))
}

private val slidePopEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { -it / 3 },
        animationSpec  = tween(300, easing = FastOutSlowInEasing)
    ) + fadeIn(tween(200))
}

private val slidePopExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(300, easing = FastOutSlowInEasing)
    ) + fadeOut(tween(160))
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    SharedTransitionLayout {
        NavHost(
            navController    = navController,
            startDestination = Routes.Home.route,
            enterTransition  = slideEnter,
            exitTransition   = slideExit,
            popEnterTransition  = slidePopEnter,
            popExitTransition   = slidePopExit
        ) {
            composable(Routes.Home.route) {
                HomeScreen(
                    onVerified      = { navController.navigate(Routes.Result.createRoute(it)) },
                    onCatalogClick  = { navController.navigate(Routes.Catalog.route) },
                    onStoryClick    = { navController.navigate(Routes.Story.route) },
                    onArtisansClick = { navController.navigate(Routes.ArtisanList.route) },
                    onFavoritesClick = { navController.navigate(Routes.Favorites.route) }
                )
            }
            composable(
                route     = Routes.Result.route,
                arguments = listOf(navArgument("toyId") { type = NavType.StringType })
            ) {
                ResultScreen(
                    toyId         = it.arguments?.getString("toyId") ?: "",
                    onArtisanClick = { id -> navController.navigate(Routes.ArtisanProfile.createRoute(id)) },
                    onBack         = { navController.popBackStack() }
                )
            }
            composable(Routes.Catalog.route) {
                CatalogScreen(
                    onToyClick = { navController.navigate(Routes.ToyDetail.createRoute(it)) },
                    onBack     = { navController.popBackStack() },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable
                )
            }
            composable(Routes.Favorites.route) {
                FavoritesScreen(
                    onToyClick = { navController.navigate(Routes.ToyDetail.createRoute(it)) },
                    onBack     = { navController.popBackStack() },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable
                )
            }
            composable(Routes.ArtisanList.route) {
                ArtisanListScreen(
                    onArtisanClick = { navController.navigate(Routes.ArtisanProfile.createRoute(it)) },
                    onBack         = { navController.popBackStack() },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable
                )
            }
            composable(
                route     = Routes.ToyDetail.route,
                arguments = listOf(navArgument("toyId") { type = NavType.StringType })
            ) {
                ToyDetailScreen(
                    toyId          = it.arguments?.getString("toyId") ?: "",
                    onArtisanClick = { id -> navController.navigate(Routes.ArtisanProfile.createRoute(id)) },
                    onBack         = { navController.popBackStack() },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable
                )
            }
            composable(
                route     = Routes.ArtisanProfile.route,
                arguments = listOf(navArgument("artisanId") { type = NavType.StringType })
            ) { back ->
                val artisanId = back.arguments?.getString("artisanId") ?: ""
                ArtisanProfileScreen(
                    artisanId  = artisanId,
                    onMapClick = { navController.navigate(Routes.MapScreen.createRoute(artisanId)) },
                    onBack     = { navController.popBackStack() },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable
                )
            }
            composable(
                route     = Routes.MapScreen.route,
                arguments = listOf(navArgument("artisanId") { type = NavType.StringType })
            ) {
                MapScreen(
                    artisanId = it.arguments?.getString("artisanId") ?: "",
                    onBack    = { navController.popBackStack() }
                )
            }
            composable(Routes.Story.route) {
                StoryScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}
