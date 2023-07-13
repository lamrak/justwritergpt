package net.validcat.justwriter.feature.notes

import androidx.compose.runtime.Immutable
import net.validcat.justwriter.core.model.data.Note


@Immutable
data class NoteItem(
    val note: Note,
    val isSelected: Boolean = false
)
