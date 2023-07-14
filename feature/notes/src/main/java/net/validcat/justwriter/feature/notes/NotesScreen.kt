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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun NotesRoute(
//    viewModel: NotesViewModel = hiltViewModel(),
//    onSettingsClick: () -> Unit,
//    onAddNoteClick: () -> Unit,
//    onNoteClick: (Int) -> Unit
) {
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NotesScreen(
//        uiState = uiState,
//        onEvent = viewModel::onEvent,
//        onSettingsClick = onSettingsClick,
//        onAddNoteClick = onAddNoteClick,
//        onNoteClick = onNoteClick
    )
}
@OptIn(ExperimentalFoundationApi::class)
@Suppress("LongMethod")
@Composable
fun NotesScreen(
//    uiState: NotesViewModel.NotesUiState,
//    onEvent: (NotesViewModel.NotesEvent) -> Unit,
//    onSettingsClick: () -> Unit,
//    onAddNoteClick: () -> Unit,
//    onNoteClick: (Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumnSample(modifier = Modifier.padding(all = 8.dp))
    }
}


@Composable
fun LazyColumnSample(modifier: Modifier) {
    val itemsList = (0..5).toList()
    val itemsIndexedList = listOf("A", "B", "C")

    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text("Header")
        }

        items(itemsList) {
            LazyListItem("Item is $it")
        }

        itemsIndexed(itemsIndexedList) { index, item ->
            Text("Item at index $index is $item")
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