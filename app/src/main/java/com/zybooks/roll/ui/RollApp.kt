package com.zybooks.roll.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zybooks.roll.ui.screens.CategoryDetailsScreen
import com.zybooks.roll.ui.screens.CreateActivityScreen
import com.zybooks.roll.ui.screens.DeckScreen
import com.zybooks.roll.ui.screens.CreateCategoryScreen
import com.zybooks.roll.viewmodel.DeckViewModel
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@kotlinx.serialization.InternalSerializationApi
sealed class Routes {
    @Serializable
    data object Deck

    @Serializable
    data object CreateCategory

    @Serializable
    data class CategoryDetail(val categoryId: Int)

    @Serializable
    data class CreateActivity(val categoryId: Int)

}

@OptIn(InternalSerializationApi::class)
@Composable
fun RollApp(
) {
    val navController = rememberNavController()
    val deckViewModel: DeckViewModel = viewModel()


    NavHost(
        navController = navController,
        startDestination = Routes.Deck
    ) {
        composable<Routes.Deck> {
            DeckScreen(
                navController = navController,
                viewModel = deckViewModel,
                onAddCategoryClick = {
                    navController.navigate(Routes.CreateCategory)
                }
            )
        }
        composable<Routes.CreateCategory> {
            CreateCategoryScreen(
                navController = navController,
                viewModel = deckViewModel
            )
        }
        composable<Routes.CategoryDetail> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.CategoryDetail>()

            CategoryDetailsScreen(
                navController = navController,
                viewModel = deckViewModel,
                categoryId = args.categoryId,
                onAddActivityClick = {
                    navController.navigate(
                        Routes.CreateActivity(args.categoryId)
                    )
                }
            )
        }
        composable<Routes.CreateActivity> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.CreateActivity>()

            CreateActivityScreen(
                navController = navController,
                viewModel = deckViewModel,
                categoryId = args.categoryId
            )
        }
    }
}