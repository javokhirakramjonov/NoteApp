package uz.gita.noteapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp.useCase.NotesPageUseCase
import uz.gita.noteapp.useCase.useCaseImpl.NotesPageUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NotesPageUseCaseModule {

    @[Binds Singleton]
    fun get(impl: NotesPageUseCaseImpl) : NotesPageUseCase
}