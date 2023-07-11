package net.validcat.justwriter.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.validcat.justwriter.core.data.repository.LocalNoteRepository
import net.validcat.justwriter.core.data.repository.NoteRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNoteRepository(
        topicsRepository: LocalNoteRepository,
    ): NoteRepository
}