package uz.gita.noteapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp.useCase.AddNoteScreenUseCase
import uz.gita.noteapp.useCase.useCaseImpl.AddNoteScreenUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface EditorNoteUseCaseModule {
    @[Binds Singleton]
    fun getEditorNote(impl: AddNoteScreenUseCaseImpl) : AddNoteScreenUseCase
}