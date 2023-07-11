package net.validcat.justwriter.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(
    tableName = "note"
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstWord: String,
    val secondWord: String,
    val thirdWord: String,
    val isPinned: Boolean,
    val color: Int,
    val createDate: Instant,
)