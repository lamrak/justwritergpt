package net.validcat.justwriter.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.validcat.justwriter.core.database.entity.StoryEntity

@Dao
interface StoryDao {
    @Query(value = "SELECT * FROM story WHERE id =:id")
    fun getStory(id: Int): Flow<StoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(storyEntity: StoryEntity)

    @Query(value = "DELETE  FROM story WHERE id in (:storyIds)")
    suspend fun deleteStory(storyIds: List<Int>)
}