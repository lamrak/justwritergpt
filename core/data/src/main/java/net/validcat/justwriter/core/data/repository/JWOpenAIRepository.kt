package net.validcat.justwriter.core.data.repository

import android.util.Log
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionChunk
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.Model
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import net.validcat.justwriter.core.data.mapper.toModel
import net.validcat.justwriter.core.database.dao.NoteDao
import net.validcat.justwriter.core.model.data.Note
import net.validcat.justwriter.core.network.AppDispatchers
import net.validcat.justwriter.core.network.Dispatcher
import javax.inject.Inject

class JWOpenAIRepository @Inject constructor(
    private val noteDao: NoteDao,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : OpenAIRepository {
    @OptIn(BetaOpenAI::class)
    override suspend fun getOverview(): Flow<ChatCompletionChunk> {
            val openAI = OpenAI("sk-")

            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.System,
                        content = "You are a helpful assistant!"
                    ),
                    ChatMessage(
                        role = ChatRole.User,
                        content = "Hello!"
                    )
                )
            )
            val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
// or, as flow
//            val chatCompletions = openAI.chatCompletions(chatCompletionRequest)

            Log.d(this.toString(), "Creating chat completions stream...")
            return openAI.chatCompletions(chatCompletionRequest)
//                .onEach { print(it.choices.first().delta?.content.orEmpty()) }
//                .onCompletion {
//                    Log.d(this.toString(), "Creating chat completions stream...")
//                }
//                .launchIn(this)
//                .join()
    }
}

//{
//    "model": "gpt-3.5-turbo",
//    "messages": [],
//    "max_tokens": 256,
//    "top_p": 1,
//    "frequency_penalty": 0,
//    "presence_penalty": 0
//}
