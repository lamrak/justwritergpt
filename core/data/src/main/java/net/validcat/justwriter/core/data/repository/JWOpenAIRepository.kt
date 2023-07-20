package net.validcat.justwriter.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import net.validcat.justwriter.core.data.mapper.toModel
import net.validcat.justwriter.core.database.dao.NoteDao
import net.validcat.justwriter.core.model.data.Note
import javax.inject.Inject

class JWOpenAIRepository @Inject constructor(
    private val noteDao: NoteDao
) : OpenAIRepository {
    override fun getOverview(): Flow<Note> {
        return flow {
            Note(1, "first", "second", "third",  -1, false, Clock.System.now())
        }
    }

}