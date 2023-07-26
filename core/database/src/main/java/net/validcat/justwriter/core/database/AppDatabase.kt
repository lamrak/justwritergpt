package net.validcat.justwriter.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.validcat.justwriter.core.database.dao.NoteDao
import net.validcat.justwriter.core.database.dao.OverviewDao
import net.validcat.justwriter.core.database.entity.ChoiceEntity
import net.validcat.justwriter.core.database.entity.MessageEntity
import net.validcat.justwriter.core.database.entity.NoteEntity
import net.validcat.justwriter.core.database.entity.OverviewEntity
import net.validcat.justwriter.core.database.utils.InstantConverter

@Database(
    entities = [
        NoteEntity::class,
        OverviewEntity::class,
        ChoiceEntity::class,
        MessageEntity::class,
    ],
    version = 1,
    autoMigrations = [],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun overviewDao(): OverviewDao
}