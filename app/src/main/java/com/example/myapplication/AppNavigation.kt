package com.example.myapplication
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.composable.BuildHomePageView
import com.example.myapplication.composable.HomePage
import com.example.myapplication.composable.ShowDetailView

@Composable
fun AppNavigation() {
  val navController = rememberNavController()

  NavHost(navController, startDestination = "home") {
    composable("home") { BuildHomePageView(navController) }
    composable("details/{itemId}") { backStackEntry ->
      val itemId = backStackEntry.arguments?.getString("itemId")
      ShowDetailView(itemId = BlogPost("$itemId","",""),)
    }

    // Add other composable destinations and their corresponding composable functions here.
  }
}
