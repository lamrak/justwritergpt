package net.validcat.justwriter.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.validcat.justwriter.core.network.model.OpenAIResponse
import net.validcat.justwriter.core.network.NetworkDataSource
import net.validcat.justwriter.core.network.model.OpenAIRequest
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitJWNetworkApi {
    @POST(value = "/v1/chat/completions")
    suspend fun getTopics(
        @Header("Authorization") token: String,
        @Body body: OpenAIRequest
    ): OpenAIResponse
}

@Singleton
class RetrofitNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : NetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl("https://api.openai.com/") //TODO Move to BuildConfig
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitJWNetworkApi::class.java)

    override suspend fun getTopics(token: String, body: OpenAIRequest): OpenAIResponse =
        networkApi.getTopics(token = token, body = body)
}
