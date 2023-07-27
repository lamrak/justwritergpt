package net.validcat.justwriter.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.validcat.justwriter.core.model.data.Story

@Entity(
    tableName = "story"
)
data class StoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val role: String,
    val content: String
)

fun StoryEntity.toModel() = Story(
    id = id,
    role = role,
    content = content,
)
