package net.validcat.justwriter.core.data.mapper

import net.validcat.justwriter.core.database.entity.StoryEntity
import net.validcat.justwriter.core.model.data.Story
import net.validcat.justwriter.core.network.model.OpenAIResponse


fun OpenAIResponse.asEntity() = StoryEntity(
    id = 0,
    role = choices.first().message.role,
    content = choices.first().message.content,
)

fun Story.asEntity() = StoryEntity(
    id = id,
    role = role,
    content = content,
)
