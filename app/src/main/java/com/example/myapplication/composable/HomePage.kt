package com.example.myapplication.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.BlogPost
import com.example.myapplication.QueryViewModel
import fetchPosts
import queryPost




@Composable
fun BuildHomePageView(navController: NavController){
    var dataLoaded = remember { mutableStateOf(false) }
    var bloglist = remember { mutableStateOf<List<BlogPost?>>(emptyList()) }

    LaunchedEffect(Unit) {
        // Run the suspend function in a coroutine scope
      val bloglist =   fetchPosts()

        // Mark the data as loaded
        dataLoaded.value = true
    }

    // Display a loading screen until the data is loaded
    if (!dataLoaded.value) {
        LoadingScreen()
    } else {
        // Your main content when data is loaded
        HomePage(bloglist = bloglist.value ,navController)
    }
}

@Composable
fun LoadingScreen() {
    // Replace this with your custom loading UI
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun HomePage(bloglist:List<BlogPost?>,navController: NavController) {
    val viewModel: QueryViewModel = viewModel()

    val searchQuery = remember { mutableStateOf("") }
    var searchResult = remember { mutableStateOf<List<BlogPost?>>(emptyList()) }
    val TOKEN = "YOUR API KEY"
    val navController = rememberNavController()


    Scaffold(
        topBar = {
            AppBar()

        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
                .padding(16.dp)
        ) {
            Text(
                text = "Browse through our Knowledge Base featuring frequently asked questions, tutorials, and other self-help resources to find the answers you need.",
                style = MaterialTheme.typography.h4,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            SearchField(searchQuery.value) { newValue ->
                searchQuery.value = newValue
              viewModel.onSearchButtonClicked(searchQuery = searchQuery)


            }
            Spacer(modifier = Modifier.height(16.dp))
            ListView(articleList =bloglist , queriedList = searchResult.value, navController =navController )
        }
    }
}

@Composable
fun AppBar() {
    TopAppBar(
        title = {
            Text(text = "Holter")
        }
    )
}

@Composable
fun SearchField(query: String, onValueChange: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = { onValueChange },
        label = { Text(text = "Search") },
        modifier = Modifier.fillMaxWidth()
    )
}
@Composable
fun ListView(articleList: List<BlogPost?>, queriedList: List<BlogPost?> = emptyList(),navController: NavHostController) {
    LazyColumn {
        // If a queried model is provided, display only that model in the list.
        items(listOf(queriedList)) {
            queriedList.forEach { item ->
                item?.let {
                    ListItem(item,onClick = {
                        // Navigate to another page when the ListItem is clicked
                        navController.navigate("detail/${item.id}")
                    })
                }
            }
        }
    }
}


@Composable
fun ListItem(item: BlogPost?,onClick: () -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), elevation = 4.dp
    ) {
        if (item != null) {
            Text(
                text = item.title,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable(onClick = onClick), // Attach the click event
                style = MaterialTheme.typography.body1
            )
        }
    }
}

//@Preview
//@Composable
//fun PreviewHomePage() {
//    HomePage(bloglist = emptyList(), navController = null)
//}

