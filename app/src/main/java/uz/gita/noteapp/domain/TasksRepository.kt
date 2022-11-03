package uz.gita.noteapp.domain

import uz.gita.noteapp.data.entity.TasksEntity

interface TasksRepository {
    suspend fun getTasks(): List<TasksEntity>
    suspend fun addTask(oldDate: String, task: List<TasksEntity>)
    suspend fun delete(task: List<TasksEntity>)
}