package uz.gita.noteapp.data.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.noteapp.data.dao.NoteDao
import uz.gita.noteapp.data.dao.TasksDao
import uz.gita.noteapp.data.entity.NoteEntity
import uz.gita.noteapp.data.entity.TasksEntity

@Database(entities = [NoteEntity::class, TasksEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao
    abstract fun getTasksDao(): TasksDao

    companion object {
        private lateinit var instance: AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            if (::instance.isInitialized)
                return instance
            instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "NotesAndTasks"
            )
                .allowMainThreadQueries()
                .build()

            return instance
        }
    }
}