import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.BlogPost
import kotlinx.coroutines.awaitAll
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST


const val TOKEN  = "YOUR API KEY"

interface ButterCmsApiService {
    @GET("-YOUR ENDPOINT-")
    suspend fun getBlogPosts(
        @Query("auth_token") authToken: String,
    ): Response<List<BlogPost>>


    // New method for searching blog posts
    @POST("pages/drop_knowledge_base/")
    suspend fun searchBlogPosts(
        @Query("auth_token") authToken: String,
        @Query("fields.category.slug") query: String,
    ): Response<List<BlogPost>>

}


class FetchResult(val posts: List<BlogPost>?, val errorMessage: String?)

suspend fun fetchPosts(): FetchResult {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.example.com/") // Replace with your base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ButterCmsApiService::class.java)

    try {
        val response = apiService.getBlogPosts(TOKEN)
        return if (response.isSuccessful) {
            FetchResult(
                posts = response.body(),
                errorMessage = null
            )
        } else {
            FetchResult(
                posts = null,
                errorMessage = "API error: ${response.message()}"
            )
        }
    } catch (e: Exception) {
        return FetchResult(
            posts = null,
            errorMessage = "Network or other exception: ${e.message}"
        )
    }
}

class QueryResult(val posts: MutableState<List<BlogPost?>>, val errorMessage: String?)

suspend fun queryPost(searchQuery: String): QueryResult {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.buttercms.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val butterCmsApiService = retrofit.create(ButterCmsApiService::class.java)

    try {
        val response = butterCmsApiService.searchBlogPosts(TOKEN, searchQuery)
        return if (response.isSuccessful) {
            QueryResult(
                posts = mutableStateOf(response.body() ?: emptyList()),
                errorMessage = null
            )
        } else {
            QueryResult(
                posts = mutableStateOf(emptyList()),
                errorMessage = "API error: ${response.message()}"
            )
        }
    } catch (e: Exception) {
        return QueryResult(
            posts = mutableStateOf(emptyList()),
            errorMessage = "Network or other exception: ${e.message}"
        )
    }
}
