package net.validcat.justwriter.core.data.repository

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.validcat.justwriter.core.database.dao.NoteDao
import net.validcat.justwriter.core.network.AppDispatchers
import net.validcat.justwriter.core.network.Dispatcher
import net.validcat.justwriter.core.network.JWNetworkDataSource
import net.validcat.justwriter.core.network.model.GPTSettings
import net.validcat.justwriter.core.network.model.UserContent
import javax.inject.Inject

class JWOpenAIRepository @Inject constructor(
    private val noteDao: NoteDao,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkDataSource: JWNetworkDataSource
) : OpenAIRepository {

    override suspend fun getOverview(): Flow<Any> {
        val data = GPTSettings(
            model = "gpt-3.5-turbo",
            messages = listOf(
                UserContent(role = "user", content = "When Italy won FIFA World Cup?")
            ),
            maxTokens = 256,
            topP = 1
        )

        try {
            Log.d("GPT", "init")
            val topic = networkDataSource.getTopics(
                "Bearer ",
                data
            )

            Log.d("GPT", topic.choices.size.toString())
        } catch (e: Exception) {
            Log.d("", e.toString())
        }

        return flow {
            Any()
        }
//            val openAI = OpenAI()
//
//            val chatCompletionRequest = ChatCompletionRequest(
//                model = ModelId("gpt-3.5-turbo"),
//                messages = listOf(
//                    ChatMessage(
//                        role = ChatRole.System,
//                        content = "You are a helpful assistant!"
//                    ),
//                    ChatMessage(
//                        role = ChatRole.User,
//                        content = "Hello!"
//                    )
//                )
//            )
//            val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
//// or, as flow
////            val chatCompletions = openAI.chatCompletions(chatCompletionRequest)
//
//            Log.d(this.toString(), "Creating chat completions stream...")
//            return openAI.chatCompletions(chatCompletionRequest)
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
