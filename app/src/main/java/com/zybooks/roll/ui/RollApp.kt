package com.zybooks.roll.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zybooks.roll.ui.screens.DeckScreen
import com.zybooks.roll.ui.screens.CreateCategoryScreen
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@kotlinx.serialization.InternalSerializationApi
sealed class Routes {
    @Serializable
    data object Deck

    @Serializable
    data object CreateCategory
}

@OptIn(InternalSerializationApi::class)
@Composable
fun RollApp(
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Deck
    ) {
        composable<Routes.Deck> {
            DeckScreen(
                navController = navController,
                onAddCategoryClick = {
                    navController.navigate(Routes.CreateCategory)
                }
            )
        }
        composable<Routes.CreateCategory> {
            CreateCategoryScreen(navController)
        }
    }
}