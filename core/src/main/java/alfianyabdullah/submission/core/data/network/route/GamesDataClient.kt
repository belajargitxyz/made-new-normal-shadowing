package alfianyabdullah.submission.core.data.network.route

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GamesDataClient {
    private val hostname = "api.rawg.io"
    private val certificatePinner = CertificatePinner.Builder()
        .add(
            hostname,
            "sha256/R+V29DqDnO269dFhAAB5jMlZHepWpDGuoejXJjprh7A=",
            "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=",
            "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o="
        )
        .build()

    private val client: OkHttpClient
        get() {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .certificatePinner(certificatePinner)
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