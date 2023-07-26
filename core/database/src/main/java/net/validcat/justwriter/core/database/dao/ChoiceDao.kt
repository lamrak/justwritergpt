package net.validcat.justwriter.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import net.validcat.justwriter.core.database.entity.ChoiceEntity
import net.validcat.justwriter.core.database.entity.NoteEntity

@Dao
interface ChoiceDao {
    @Query(value = "SELECT * FROM choice WHERE id = :choiceId")
    fun getChoiceEntity(choiceId: String): Flow<ChoiceEntity>

    @Query(value = "SELECT * FROM choice")
    fun getChoiceEntities(): Flow<List<ChoiceEntity>>

    @Query(value = "SELECT * FROM choice")
    suspend fun getOneOffChoiceEntities(): List<ChoiceEntity>

    @Query(value = "SELECT * FROM choice WHERE id IN (:ids)")
    fun getChoiceEntities(ids: Set<String>): Flow<List<ChoiceEntity>>

    @Query(value = "DELETE FROM choice WHERE id in (:ids)")
    suspend fun deleteChoices(ids: List<String>)
}