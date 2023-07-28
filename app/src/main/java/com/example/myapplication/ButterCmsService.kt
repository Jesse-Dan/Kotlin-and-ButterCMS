import com.example.myapplication.BlogPost
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface ButterCmsApiService {
    @GET("-YOUR ENDPOINT-")
    suspend fun getBlogPosts(
        @Query("auth_token") authToken: String,
    ): Response<List<BlogPost>>
}


suspend fun fetchBlogPosts(authToken: String) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.example.com/") // Replace with your base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ButterCmsApiService::class.java)

    try {
        val response = apiService.getBlogPosts(authToken)
        if (response.isSuccessful) {
            val blogPosts = response.body()
            // Manage blog posts
        } else {
            // Handle API errors
        }
    } catch (e: Exception) {
        // Handle network or other exceptions
    }
}
