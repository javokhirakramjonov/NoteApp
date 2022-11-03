package uz.gita.noteapp.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp.data.dao.NoteDao
import uz.gita.noteapp.data.dao.TasksDao
import uz.gita.noteapp.data.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @[Provides Singleton]
    fun getDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @[Provides Singleton]
    fun getNoteDao(database: AppDatabase) : NoteDao {
        return database.getNoteDao()
    }

    @[Provides Singleton]
    fun getTaskDao(database: AppDatabase) : TasksDao {
        return database.getTasksDao()
    }
}