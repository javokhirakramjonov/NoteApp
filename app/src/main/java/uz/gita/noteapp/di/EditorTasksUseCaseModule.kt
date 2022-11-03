package uz.gita.noteapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp.useCase.AddNoteScreenUseCase
import uz.gita.noteapp.useCase.AddTasksScreenUseCase
import uz.gita.noteapp.useCase.useCaseImpl.AddNoteScreenUseCaseImpl
import uz.gita.noteapp.useCase.useCaseImpl.AddTasksScreenUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface EditorTasksUseCaseModule {
    @[Binds Singleton]
    fun getEditorTasks(impl: AddTasksScreenUseCaseImpl) : AddTasksScreenUseCase
}