package uz.gita.noteapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp.domain.TasksRepository
import uz.gita.noteapp.domain.repotiroyImpl.TasksRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TasksRepositoryModule {

    @[Binds Singleton]
    fun getTasksImpl(impl: TasksRepositoryImpl): TasksRepository
}