package net.validcat.justwriter.core.data.repository

import kotlinx.coroutines.flow.Flow
import net.validcat.justwriter.core.model.data.Note
import javax.inject.Inject

class LocalNoteRepository @Inject constructor(
//    private val noteDao: NoteDao
) : NoteRepository {
    override fun getNote(id: Int): Flow<Note> {
        TODO("Not yet implemented")
    }

    override fun getNotes(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNotesColor(noteIds: List<Int>, color: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNotesPinned(noteIds: List<Int>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNotes(noteIds: List<Int>) {
        TODO("Not yet implemented")
    }

}