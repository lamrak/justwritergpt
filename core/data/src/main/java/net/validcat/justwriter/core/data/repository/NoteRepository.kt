package net.validcat.justwriter.core.data.repository

import kotlinx.coroutines.flow.Flow
import net.validcat.justwriter.core.model.data.Note

interface NoteRepository {
    fun getNote(id: Int): Flow<Note>
    fun getNotes(): Flow<List<Note>>
    suspend fun insertNote(note: Note)
    suspend fun updateNotesColor(noteIds: List<Int>, color: Int)
    suspend fun updateNotesPinned(noteIds: List<Int>)
    suspend fun deleteNotes(noteIds: List<Int>)
}