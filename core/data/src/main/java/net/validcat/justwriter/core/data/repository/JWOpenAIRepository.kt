package net.validcat.justwriter.core.data.repository

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.validcat.justwriter.core.database.dao.NoteDao
import net.validcat.justwriter.core.network.AppDispatchers
import net.validcat.justwriter.core.network.Dispatcher
import net.validcat.justwriter.core.network.NetworkDataSource
import net.validcat.justwriter.core.network.model.OpenAIRequest
import net.validcat.justwriter.core.network.model.UserContent
import javax.inject.Inject

class JWOpenAIRepository @Inject constructor(
    private val noteDao: NoteDao,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkDataSource: NetworkDataSource
) : OpenAIRepository {

    override suspend fun getOverview(searchPhrase: String): Flow<Any> {
        val data = OpenAIRequest(
            model = "gpt-3.5-turbo",
            messages = listOf(UserContent(role = "user", content = searchPhrase)))

        try {
            val topic = networkDataSource.getTopics("Bearer ", data)
        } catch (e: Exception) {
            Log.d(this.toString(), e.toString())
        }

        return flow {
            Any()
        }
    }
}
