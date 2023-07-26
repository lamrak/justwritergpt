package net.validcat.justwriter.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.validcat.justwriter.core.database.entity.OverviewEntity

@Dao
interface OverviewDao {
    @Query(value = "SELECT * FROM overview WHERE id =:id")
    fun getOverview(id: Int): Flow<OverviewEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOverview(overviewEntity: OverviewEntity)

    @Query(value = "DELETE  FROM overview WHERE id in (:overviewIds)")
    suspend fun deleteOverviews(overviewIds: List<Int>)
}