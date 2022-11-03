package uz.gita.noteapp.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp.data.entity.NoteEntity
import uz.gita.noteapp.data.entity.TasksEntity

interface TasksPageUseCase {
    fun getTasks() : Flow<Result<List<List<TasksEntity>>>>
    fun deleteTask(task: List<TasksEntity>) : Flow<Result<Unit>>
}