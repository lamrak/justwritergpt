package net.validcat.justwriter.core.network

import net.validcat.justwriter.core.network.model.OpenAIRequest
import net.validcat.justwriter.core.network.model.OpenAIResponse

interface NetworkDataSource {

    suspend fun getStory(token: String, openAIRequest: OpenAIRequest): OpenAIResponse
}
