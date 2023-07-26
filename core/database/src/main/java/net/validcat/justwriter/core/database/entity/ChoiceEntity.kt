package net.validcat.justwriter.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "choice"
)
data class ChoiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val role: String,
    val content: String
)
