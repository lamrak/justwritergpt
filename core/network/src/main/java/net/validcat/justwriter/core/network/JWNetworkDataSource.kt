package net.validcat.justwriter.core.network

import net.validcat.justwriter.core.network.model.OpenAIResponse
import net.validcat.justwriter.core.network.model.GPTSettings

interface JWNetworkDataSource {
    suspend fun getTopics(token: String, gptSettings: GPTSettings): OpenAIResponse
}
