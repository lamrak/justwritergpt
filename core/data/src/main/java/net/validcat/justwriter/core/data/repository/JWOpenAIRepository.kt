package net.validcat.justwriter.core.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.validcat.justwriter.core.data.mapper.asEntity
import net.validcat.justwriter.core.database.dao.StoryDao
import net.validcat.justwriter.core.database.entity.toModel
import net.validcat.justwriter.core.model.data.Story
import net.validcat.justwriter.core.network.AppDispatchers
import net.validcat.justwriter.core.network.Dispatcher
import net.validcat.justwriter.core.network.NetworkDataSource
import net.validcat.justwriter.core.network.model.OpenAIRequest
import net.validcat.justwriter.core.network.model.UserContent
import javax.inject.Inject

class JWOpenAIRepository @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val storyDao: StoryDao,
    private val networkDataSource: NetworkDataSource
) : OpenAIRepository {

    override suspend fun fetchOverview(searchPhrase: String) {
        val data = OpenAIRequest(
            model = "gpt-3.5-turbo",
            messages = listOf(UserContent(role = "user", content = searchPhrase))
        )

        val topic = networkDataSource.getStory("Bearer ", data)
        storyDao.insertStory(topic.asEntity())
    }

    override fun getOverview(id: Int): Flow<Story> {
        return storyDao.getStory(id).map { story ->
            if (story == null) {
                return@map Story()
            }

            story.toModel()
        }
    }
}
