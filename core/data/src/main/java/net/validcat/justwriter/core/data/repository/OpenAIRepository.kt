package net.validcat.justwriter.core.data.repository

import kotlinx.coroutines.flow.Flow
import net.validcat.justwriter.core.model.data.Note

interface OpenAIRepository {
    fun getOverview(): Flow<Note>
}
