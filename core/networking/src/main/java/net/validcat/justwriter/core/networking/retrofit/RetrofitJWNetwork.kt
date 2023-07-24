package net.validcat.justwriter.core.networking.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.validcat.justwriter.core.networking.JWNetworkDataSource
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitJWNetworkApi {
    @GET(value = "topics")
    suspend fun getTopics(
        @Query("id") ids: List<String>?,
    ): NetworkResponse<List<String>>
}


/**
 * Wrapper for data provided from the [JWBaseUrl]
 */
@Serializable
private data class NetworkResponse<T>(
    val data: T,
)

@Singleton
class RetrofitJWNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : JWNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl("")
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitJWNetworkApi::class.java)

    override suspend fun getTopics(ids: List<String>?): List<String> =
        networkApi.getTopics(ids = ids).data
}
