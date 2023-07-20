package net.validcat.justwriter.core.data.mapper
import kotlinx.datetime.Clock
import kotlinx.serialization.json.JsonNull.content
import net.validcat.justwriter.core.database.entity.NoteEntity
import net.validcat.justwriter.core.model.data.Note

fun Note.toEntity() = NoteEntity(
    id = id,
    firstWord = firstWord,
    secondWord = firstWord,
    thirdWord = thirdWord,
    isPinned = isPinned,
    color = color,
    createDate = createDate
)

fun NoteEntity?.toModel() = Note(
    id = this?.id ?: 0,
    firstWord = this?.firstWord ?: "",
    secondWord = this?.secondWord ?: "",
    thirdWord = this?.thirdWord ?: "",
    isPinned = this?.isPinned ?: false,
    color = this?.color ?: -1,
    createDate = this?.createDate ?: Clock.System.now()
)