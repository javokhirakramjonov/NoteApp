package uz.gita.noteapp.useCase.useCaseImpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteapp.data.entity.TasksEntity
import uz.gita.noteapp.domain.TasksRepository
import uz.gita.noteapp.useCase.TasksPageUseCase
import javax.inject.Inject

class TasksPageUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : TasksPageUseCase {

    override fun getTasks() = flow<Result<List<List<TasksEntity>>>> {
        val data = repository.getTasks()
        val answer = ArrayList<ArrayList<TasksEntity>>()
        var temp = ArrayList<TasksEntity>()

        if (data.isNotEmpty()) {
            var tempId = data[0].category
            temp.add(data[0])
            for (i in 1 until data.size) {
                if (data[i].category == tempId) {
                    temp.add(data[i])
                } else {
                    if (temp.isNotEmpty())
                        answer.add(temp)
                    temp = ArrayList<TasksEntity>()
                    temp.add(data[i])
                    tempId = data[i].category
                }
            }
            if (temp.isNotEmpty())
                answer.add(temp)
        }
        emit(Result.success(answer))
    }.flowOn(Dispatchers.IO)

    override fun deleteTask(task: List<TasksEntity>) = flow<Result<Unit>> {
        repository.delete(task)
        emit(Result.success(Unit))
    }.flowOn(Dispatchers.IO)
}