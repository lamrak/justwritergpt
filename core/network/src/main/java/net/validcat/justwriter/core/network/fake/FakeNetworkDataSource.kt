package net.validcat.justwriter.core.network.fake

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import net.validcat.justwriter.core.network.AppDispatchers
import net.validcat.justwriter.core.network.Dispatcher
import net.validcat.justwriter.core.network.JvmUnitTestFakeAssetManager
import net.validcat.justwriter.core.network.NetworkDataSource
import net.validcat.justwriter.core.network.model.OpenAIRequest
import net.validcat.justwriter.core.network.model.OpenAIResponse
import net.validcat.justwriter.core.network.utils.NetworkChangeList
import javax.inject.Inject

class FakeNetworkDataSource @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : NetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getTopics(token: String, openAIRequest: OpenAIRequest): OpenAIResponse =
        withContext(context = ioDispatcher) {
            assets.open(RESPONSE_OPENAI_ASSET).use(networkJson::decodeFromStream)
        }

    companion object {
        private const val RESPONSE_OPENAI_ASSET = "response.json"
        private const val TOPICS_ASSET = "topics.json"
    }
}

/**
 * Converts a list of [T] to change list of all the items in it where [idGetter] defines the
 * [NetworkChangeList.id]
 */
private fun <T> List<T>.mapToChangeList(
    idGetter: (T) -> String,
) = mapIndexed { index, item ->
    NetworkChangeList(
        id = idGetter(item),
        changeListVersion = index,
        isDelete = false,
    )
}
