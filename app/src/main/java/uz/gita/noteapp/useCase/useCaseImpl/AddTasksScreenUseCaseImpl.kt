package uz.gita.noteapp.useCase.useCaseImpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteapp.data.entity.TasksEntity
import uz.gita.noteapp.domain.TasksRepository
import uz.gita.noteapp.useCase.AddTasksScreenUseCase
import javax.inject.Inject

class AddTasksScreenUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : AddTasksScreenUseCase {

    override fun saveTasks(oldDate: String, task: List<TasksEntity>) = flow<Result<Unit>> {
        repository.addTask(oldDate, task)
        emit(Result.success(Unit))
    }.flowOn(Dispatchers.IO)
}