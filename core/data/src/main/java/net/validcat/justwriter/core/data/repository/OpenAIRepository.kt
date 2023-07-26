package net.validcat.justwriter.core.data.repository

import kotlinx.coroutines.flow.Flow

interface OpenAIRepository {

    suspend fun getOverview(searchPhrase: String): Flow<Any>
}
