package alfianyabdullah.submission.core.data.network.route

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GamesDataClient {
    private val client: OkHttpClient
        get() {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        }

    val service: GamesDataService
        get() {
            return Retrofit.Builder()
                .baseUrl("https://api.rawg.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(GamesDataService::class.java)
        }
}