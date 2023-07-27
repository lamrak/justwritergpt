package net.validcat.justwriter.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.validcat.justwriter.core.data.fake.FakeOpenAIRepository
import net.validcat.justwriter.core.data.repository.LocalNoteRepository
import net.validcat.justwriter.core.data.repository.LocalUserDataRepository
import net.validcat.justwriter.core.data.repository.NoteRepository
import net.validcat.justwriter.core.data.repository.OpenAIRepository
import net.validcat.justwriter.core.data.repository.UserDataRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNoteRepository(
        topicsRepository: LocalNoteRepository,
    ): NoteRepository

    @Binds
    fun bindsUserDataRepository(
        userDataRepository: LocalUserDataRepository,
    ): UserDataRepository

    @Binds
    fun bindsOpenAIRepository(
        userDataRepository: FakeOpenAIRepository,
    ): OpenAIRepository
}