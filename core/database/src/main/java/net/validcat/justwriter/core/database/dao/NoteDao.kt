package net.validcat.justwriter.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.validcat.justwriter.core.database.entity.NoteEntity

@Dao
interface NoteDao {
    @Query(value = "SELECT * FROM note WHERE id =:id")
    fun getNote(id: Int): Flow<NoteEntity>

    @Query(value = "SELECT * FROM note")
    fun getNotes(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Query(value = "UPDATE note SET color = :color WHERE id in (:noteIds)")
    suspend fun updateNotesColor(noteIds: List<Int>, color: Int)

    @Query(value = "UPDATE note SET isPinned = NOT isPinned WHERE id in (:noteIds)")
    suspend fun updateNotesPinned(noteIds: List<Int>)

    @Query(value = "DELETE  FROM note WHERE id in (:noteIds)")
    suspend fun deleteNotes(noteIds: List<Int>)
}