package net.validcat.justwriter.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.validcat.justwriter.core.database.AppDatabase
import net.validcat.justwriter.core.database.dao.NoteDao
import net.validcat.justwriter.core.database.dao.OverviewDao

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesNoteDao(
        database: AppDatabase,
    ): NoteDao = database.noteDao()

    @Provides
    fun providesOverviewDao(
        database: AppDatabase,
    ): OverviewDao = database.overviewDao()
}