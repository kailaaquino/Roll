package com.zybooks.roll.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zybooks.roll.data.model.NavItem
import com.zybooks.roll.ui.screens.ActivityDetailsScreen
import com.zybooks.roll.ui.screens.CategoryDetailsScreen
import com.zybooks.roll.ui.screens.CreateActivityScreen
import com.zybooks.roll.ui.screens.DeckScreen
import com.zybooks.roll.ui.screens.CreateCategoryScreen
import com.zybooks.roll.ui.screens.RollScreen
import com.zybooks.roll.ui.screens.RolledActivityScreen
import com.zybooks.roll.viewmodel.DeckViewModel
import com.zybooks.roll.viewmodel.RollViewModel
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@kotlinx.serialization.InternalSerializationApi
sealed class Routes {
    @Serializable
    data object Deck

    @Serializable
    data object Roll

    @Serializable
    data object CreateCategory

    @Serializable
    data class CategoryDetail(val categoryId: Int)

    @Serializable
    data class CreateActivity(val categoryId: Int)

    @Serializable
    data class ActivityDetails(val activityId: Int)

    @Serializable
    data class RolledActivity(
        val activityId: Int,
        val categoryId: Int? = null,
        val sourceScreen: String)
}

@OptIn(InternalSerializationApi::class)
@Composable
fun RollApp(
) {
    val navController = rememberNavController()

    val deckViewModel: DeckViewModel = viewModel()
    val rollViewModel: RollViewModel = viewModel()


    val navItemList = listOf(
        NavItem(
            name = "Roll",
            icon = Icons.Filled.Home
        ),
        NavItem(
            name = "Deck",
            icon = Icons.Filled.Bookmarks
        )
    )

    var selectedNavItemIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedNavItemIndex == index,
                        onClick = {
                            selectedNavItemIndex = index
                            when (item.name) {
                                "Roll" -> navController.navigate(Routes.Roll)
                                "Deck" -> navController.navigate(Routes.Deck)
                            }
                                  },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        },
                        label = { Text(item.name) }
                    )
                }
            }
        }
    ) { innerPadding ->


        NavHost(
            navController = navController,
            startDestination = Routes.Roll,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Routes.Roll>{
                RollScreen(
                    navController = navController,
                    deckViewModel = deckViewModel,
                    rollViewModel = rollViewModel
                )
            }
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
            composable<Routes.ActivityDetails> { backStackEntry ->
                val args = backStackEntry.toRoute<Routes.ActivityDetails>()
                val activity = deckViewModel.getActivityById(args.activityId)

                if (activity != null) {
                    ActivityDetailsScreen(
                        navController = navController,
                        viewModel = deckViewModel,
                        activity = activity
                    )
                } else {
                    // fallback UI if somehow not found
                    Text("Activity not found")
                }
            }
            composable<Routes.RolledActivity> { backStackEntry ->
                val args = backStackEntry.toRoute<Routes.RolledActivity>()
                val activity = deckViewModel.getActivityById(args.activityId)
                val rollCategory = args.categoryId
                val source = args.sourceScreen

                if (activity != null) {
                    RolledActivityScreen(
                        navController = navController,
                        viewModel = deckViewModel,
                        activity = activity,
                        sourceScreen = source,
                        onGo = {
                            navController.navigate(Routes.ActivityDetails(activity.id))
                        },
                        onRollAgain = {
                            val newResult = if (rollCategory == null) {
                                deckViewModel.rollAnyActivity()
                            } else {
                                deckViewModel.rollActivityFromCategory(rollCategory)
                            }

                            newResult?.let {
                                navController.navigate(Routes.RolledActivity(it.id, rollCategory, source))
                            }

                        }
                    )
                } else {
                    Text("Activity not found")
                }
            }
        }
    }
}