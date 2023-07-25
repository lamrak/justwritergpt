package net.validcat.justwriter.core.data.repository

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletionChunk
import kotlinx.coroutines.flow.Flow
import net.validcat.justwriter.core.model.data.Note

interface OpenAIRepository {
    @OptIn(BetaOpenAI::class)
    suspend fun getOverview(): Flow<ChatCompletionChunk>
}
