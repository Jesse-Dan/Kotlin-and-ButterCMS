package com.example.myapplication
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QueryViewModel : ViewModel() {

  // Define the LiveData for search query and results

  val searchResult = MutableLiveData<List<BlogPost>>() // Assuming 'Post' is the type of the search result

  fun onSearchButtonClicked(searchQuery:MutableLiveData<String>) {
    val newValue = searchQuery.value ?: ""
    viewModelScope.launch {
      searchResult.value = queryPost(newValue)
    }
  }

  private suspend fun queryPost(searchQuery: String): List<BlogPost> {
   return queryPost(searchQuery=searchQuery)
  }

}
