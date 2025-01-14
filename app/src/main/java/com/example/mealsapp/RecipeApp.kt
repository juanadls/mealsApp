package com.example.mealsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable

fun RecipeApp (navHostController: NavHostController){
    val recipeViewModel: MainViewModel = viewModel( )
    val viewstate by recipeViewModel.categoriesState

  NavHost(navController = navHostController, startDestination = Screen.RecipeScreen.route) {
      composable(route = Screen.RecipeScreen.route){ RecipeScreen( navigateToDetail = {
          navHostController.currentBackStackEntry?.savedStateHandle?.set("category", it)
          navHostController.navigate(Screen.DetailScreen.route)
      }, viewState = viewstate) }

      composable(route = Screen.DetailScreen.route){
          val category = navHostController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")
              ?: Category("", "", "", "")
          CategoryDetailScreen(category = category)
      }
  }

}