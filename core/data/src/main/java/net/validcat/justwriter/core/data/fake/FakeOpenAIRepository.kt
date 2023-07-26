package net.validcat.justwriter.core.data.fake

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import net.validcat.justwriter.core.data.repository.OpenAIRepository
import net.validcat.justwriter.core.database.dao.NoteDao
import net.validcat.justwriter.core.network.AppDispatchers
import net.validcat.justwriter.core.network.Dispatcher
import net.validcat.justwriter.core.network.NetworkDataSource
import net.validcat.justwriter.core.network.fake.FakeNetworkDataSource
import net.validcat.justwriter.core.network.model.OpenAIRequest
import net.validcat.justwriter.core.network.model.UserContent
import javax.inject.Inject

class FakeOpenAIRepository @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkDataSource: FakeNetworkDataSource
) : OpenAIRepository {

    override suspend fun getOverview(searchPhrase: String): Flow<Any> {
        val data = OpenAIRequest(
            model = "gpt-3.5-turbo",
            messages = listOf(UserContent(role = "user", content = searchPhrase))
        )

        return flow {
            emit(
                networkDataSource.getTopics("", data)
            )
        }.flowOn(ioDispatcher)
    }
}
