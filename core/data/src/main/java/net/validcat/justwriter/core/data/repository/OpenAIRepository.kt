package net.validcat.justwriter.core.data.repository

import kotlinx.coroutines.flow.Flow
import net.validcat.justwriter.core.model.data.Story

interface OpenAIRepository {

    suspend fun fetchOverview(searchPhrase: String)
    fun getOverview(id: Int): Flow<Story>
}
