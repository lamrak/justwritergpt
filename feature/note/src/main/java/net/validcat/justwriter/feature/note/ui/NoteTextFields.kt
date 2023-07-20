package net.validcat.justwriter.feature.note.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import net.validcat.justwriter.core.model.data.Note
import net.validcat.justwriter.feature.note.NoteFields
import net.validcat.justwriter.feature.note.R

@Composable
fun NoteTextFields(
    modifier: Modifier = Modifier,
    note: Note?,
    onNoteChanged: (NoteFields) -> Unit
) {
    var firstWord by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(note?.firstWord ?: ""))
    }
    var secondWord by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(note?.secondWord ?: ""))
    }

    Column(
        modifier = modifier,
        content = {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = firstWord,
                onValueChange = {
                    firstWord = it
                    onNoteChanged(NoteFields(firstWord.text, secondWord.text))
                },
                placeholder = { Text(stringResource(id = R.string.label_title)) },
                maxLines = 1
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = secondWord,
                onValueChange = {
                    secondWord = it
                    onNoteChanged(NoteFields(firstWord.text, secondWord.text))
                },
                placeholder = { Text(stringResource(id = R.string.label_title)) },
                maxLines = 10,
                minLines = 10
            )
        }
    )
}