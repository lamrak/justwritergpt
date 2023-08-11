package net.validcat.justwriter.feature.notes

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.validcat.justwriter.core.designsystem.JWLoadingWheel

@Composable
fun NotesRoute(
    viewModel: NotesViewModel = hiltViewModel(),
    onAddNoteClick: () -> Unit,
    onNoteClick: (Int) -> Unit
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val noteItems by viewModel.noteItems.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getNotes()
    }

    NotesScreen(
        isLoading = isLoading,
        errorMessage = errorMessage,
        noteItems = noteItems,
        onNoteClick = viewModel::getRemoteOverview
//        onSettingsClick = onSettingsClick,
    )
}

@Composable
fun NotesScreen(
    isLoading: Boolean,
    errorMessage: String?,
    noteItems: List<NoteItem>,
    onNoteClick: () -> Unit,
//    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val isNoteSelected = remember(noteItems) {
        noteItems.any { it.isSelected }
    }

    errorMessage?.let {
        Text(text = "$errorMessage")
    }

    if (isLoading) {
        JWLoadingWheel(
            modifier = modifier,
            contentDesc = stringResource(id = R.string.loading),
        )
    } else {
        NotesScreenView(
            noteItems = noteItems,
            onNoteClick = onNoteClick,
            modifier = modifier
        )
    }
}

@Composable
fun NotesScreenView(noteItems: List<NoteItem>, onNoteClick: () -> Unit, modifier: Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumnSample(noteItems = noteItems, onNoteClick = onNoteClick, modifier = modifier)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyColumnSample(noteItems: List<NoteItem>,
                     onNoteClick: () -> Unit,
                     modifier: Modifier) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(items = noteItems) {
                LazyListItem(it.note.firstWord, it.note.secondWord, it.note.thirdWord, onNoteClick)
            }
    })
}

@Composable
fun LazyListItem(first: String, second: String, third: String, onNoteClick: () -> Unit) {
    Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        BubbleWidget(first = first, second = second, third = third)
    }
}

@Composable
fun BubbleWidget(first: String, second: String, third: String, story: String = "") {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 1.dp,
        // animateContentSize will change the Surface size gradually
        modifier = Modifier
            .animateContentSize()
            .padding(1.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color((Math.random() * 16777215).toInt() or (0xFF shl 24)))
            .padding(8.dp)) {
                BuubleItem(first)
                BuubleItem(second)
                BuubleItem(third)

            if (story.isNotEmpty())
                Text(text = story, modifier = Modifier.padding(horizontal = 12.dp))
        }
    }
}

@Composable
private fun BuubleItem(first: String) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 1.dp,
        // animateContentSize will change the Surface size gradually
        modifier = Modifier
            .animateContentSize()
            .padding(1.dp)
    ) {
        Text(
            text = first,
            modifier = Modifier
                .padding(all = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
//                Spacer(modifier = Modifier.width(8.dp))
    }
}