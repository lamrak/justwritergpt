package net.validcat.justwriter.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(
    tableName = "overview_choices",
    primaryKeys = ["overview_id", "choice_id"],
    foreignKeys = [
        ForeignKey(
            entity = OverviewEntity::class,
            parentColumns = ["id"],
            childColumns = ["overview_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = ChoiceEntity::class,
            parentColumns = ["id"],
            childColumns = ["choice_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["overview_id"]),
        Index(value = ["choice_id"]),
    ],
)
data class OverviewChoiceCrossRefEntity(
    @ColumnInfo(name = "overview_id")
    val overviewId: String,
    @ColumnInfo(name = "choice_id")
    val choiceId: String,
)
