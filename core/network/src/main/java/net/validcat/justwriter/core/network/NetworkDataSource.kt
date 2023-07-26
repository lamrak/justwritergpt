package net.validcat.justwriter.core.network

import net.validcat.justwriter.core.network.model.OpenAIResponse
import net.validcat.justwriter.core.network.model.OpenAIRequest

interface NetworkDataSource {

    suspend fun getTopics(token: String, openAIRequest: OpenAIRequest): OpenAIResponse
}