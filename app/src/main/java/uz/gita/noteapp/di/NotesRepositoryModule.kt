package uz.gita.noteapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp.domain.NotesRepository
import uz.gita.noteapp.domain.repotiroyImpl.NotesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NotesRepositoryModule {

    @[Binds Singleton]
    fun getNotesRepository(impl: NotesRepositoryImpl): NotesRepository

}