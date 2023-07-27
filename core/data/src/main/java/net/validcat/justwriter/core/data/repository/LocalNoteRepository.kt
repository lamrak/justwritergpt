package net.validcat.justwriter.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import net.validcat.justwriter.core.data.mapper.toEntity
import net.validcat.justwriter.core.data.mapper.toModel
import net.validcat.justwriter.core.database.dao.NoteDao
import net.validcat.justwriter.core.database.entity.NoteEntity
import net.validcat.justwriter.core.model.data.Note
import javax.inject.Inject

class LocalNoteRepository @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun getNote(id: Int): Flow<Note> {
        return noteDao.getNote(id).map { it.toModel() }
    }

    override fun getNotes(): Flow<List<Note>> {
//        return noteDao.getNotes().map { notes ->
//            notes
//                .sortedBy { it.createDate }
//                .map { it.toModel() }
//        }
//        //TODO mock up till gpt is on vacation

        val list = listOf(
            Note(1, "first", "second", "third",  -1, false, Clock.System.now()),
            Note(2, "first", "second", "third",  -1, false, Clock.System.now()),
            Note(3, "first", "second", "third",  -1, false, Clock.System.now()),
            Note(4, "first", "second", "third",  -1, false, Clock.System.now())
        )
        return flow {
            emit(list)
        }
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note.toEntity())
    }

    override suspend fun updateNotesColor(noteIds: List<Int>, color: Int) {
        noteDao.updateNotesColor(noteIds, color)
    }

    override suspend fun updateNotesPinned(noteIds: List<Int>) {
        noteDao.updateNotesPinned(noteIds)
    }

    override suspend fun deleteNotes(noteIds: List<Int>) {
        noteDao.deleteNotes(noteIds)
    }

}