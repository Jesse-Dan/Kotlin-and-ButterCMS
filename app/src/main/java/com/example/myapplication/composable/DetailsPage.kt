package com.example.myapplication.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.BlogPost


@Composable
fun ShowDetailView(itemId: BlogPost, ) {
  val selectedItem = remember { mutableStateOf(itemId) }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
      .padding(16.dp)
  ) {
    Text(
      text = "Detail View",
      style = MaterialTheme.typography.h4,
      color = Color.Black
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
      text = "${calculateReadingTime(selectedItem.value.content.length)} minutes read",
      style = MaterialTheme.typography.h4,
      color = Color.Black
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
      text = "Selected Item: ${selectedItem.value}",
      style = MaterialTheme.typography.body1,
      color = Color.Black
    )
  }
}



/// Calculate read time in minutes
fun calculateReadingTime(articleLength: Int, wordsPerMinute: Int = 200): Int {
  // Assuming average reading speed of 200 words per minute (you can adjust this value as needed)
  return (articleLength / wordsPerMinute) + 1 // Adding 1 to account for partial minutes
}
