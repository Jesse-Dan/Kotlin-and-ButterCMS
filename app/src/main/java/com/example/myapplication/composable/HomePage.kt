import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage() {
    val searchQuery = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            AppBar()
        },
        content = {
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
                SearchField()
                Spacer(modifier = Modifier.height(16.dp))
                ListView()
            }
        }
    )
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
fun SearchField() {
    TextField(
        value = "",
        onValueChange = { /* TODO: Implement search functionality */ },
        label = { Text(text = "Search") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ListView() {
    LazyColumn {
        items(listOf("Article", "Blog", "FAQ")) { item ->
            ListItem(item)
        }
    }
}

@Composable
fun ListItem(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview
@Composable
fun PreviewHomePage() {
    HomePage()
}

