package net.validcat.justwriter.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class OpenAIRequest(
    val model: String = "",
    val messages: List<UserContent>,
    val maxTokens: Int = 256,
    val topP: Int = 1
)

@Serializable
data class UserContent(
    val role: String,
    val content: String
)