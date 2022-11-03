package uz.gita.noteapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp.useCase.TasksPageUseCase
import uz.gita.noteapp.useCase.useCaseImpl.TasksPageUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TasksPageUseCaseModule {

    @[Binds Singleton]
    fun get(impl: TasksPageUseCaseImpl): TasksPageUseCase
}