package net.validcat.justwriter.core.model.data

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Note(
    val id: Int = 0,
    val firstWord: String = "",
    val secondWord: String = "",
    val thirdWord: String = "",
    val color: Int = -1,
    val isPinned: Boolean = false,
    val createDate: Instant = Clock.System.now()
) {
    val getTitle: String
        get() = "$firstWord, $secondWord, $thirdWord"

    val isEmpty: Boolean
        get() = firstWord.isEmpty() && secondWord.isEmpty()  && thirdWord.isEmpty()
}