package net.validcat.justwriter.feature.notes

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.validcat.justwriter.core.designsystem.JWLoadingWheel

@Composable
fun NotesRoute(
    viewModel: NotesViewModel = hiltViewModel(),
    onNoteClick: (Int) -> Unit
//    onSettingsClick: () -> Unit,
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val noteItems by viewModel.noteItems.collectAsStateWithLifecycle()

    NotesScreen(
        isLoading = isLoading,
        errorMessage = errorMessage,
        noteItems = noteItems,
        onNoteClick = viewModel::getRemoteOverview
//        onSettingsClick = onSettingsClick,
    )
}
@OptIn(ExperimentalFoundationApi::class)
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

    LaunchedEffect(Unit) {
        onNoteClick()
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
        LazyColumnSample(noteItems = noteItems, modifier = modifier)
    }
}


@Composable
fun LazyColumnSample(noteItems: List<NoteItem>,
                     modifier: Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(noteItems) {
            LazyListItem("Item is $it")
        }
    }
}

@Composable
fun LazyListItem(str: String) {
    Row(modifier = Modifier.padding(all = 4.dp)) {
        Image(
            painter = painterResource(androidx.core.R.drawable.ic_call_answer),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = "",
        )

        Box(
            modifier = Modifier.clickable { isExpanded = isExpanded.not() }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    "Item is $str",
                    modifier = if (isExpanded) Modifier
                        .padding(all = 8.dp)
                        .fillMaxWidth() else Modifier.padding(all = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}