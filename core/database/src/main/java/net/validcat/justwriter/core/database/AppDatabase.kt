package net.validcat.justwriter.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.validcat.justwriter.core.database.dao.NoteDao
import net.validcat.justwriter.core.database.dao.StoryDao
import net.validcat.justwriter.core.database.entity.NoteEntity
import net.validcat.justwriter.core.database.entity.StoryEntity
import net.validcat.justwriter.core.database.utils.InstantConverter

@Database(
    entities = [
        NoteEntity::class,
        StoryEntity::class
    ],
    version = 3,
    autoMigrations = [],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun storyDao(): StoryDao
}