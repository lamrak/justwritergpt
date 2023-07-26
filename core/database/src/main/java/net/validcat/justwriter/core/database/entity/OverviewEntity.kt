package net.validcat.justwriter.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(
    tableName = "overview"
)
data class OverviewEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val choices: List<ChoiceEntity>
)
