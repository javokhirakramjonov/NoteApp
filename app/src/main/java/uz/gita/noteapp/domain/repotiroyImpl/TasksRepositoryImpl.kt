package uz.gita.noteapp.domain.repotiroyImpl

import uz.gita.noteapp.data.dao.TasksDao
import uz.gita.noteapp.data.database.AppDatabase
import uz.gita.noteapp.data.entity.TasksEntity
import uz.gita.noteapp.domain.TasksRepository
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val tasks : TasksDao
) : TasksRepository {

    override suspend fun getTasks(): List<TasksEntity> {
        val cursor = tasks.getTasks()
        val data = ArrayList<TasksEntity>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                cursor.apply {
                    data.add(
                        TasksEntity(
                            getInt(0),
                            getString(1),
                            getString(2),
                            getInt(3),
                            getString(4)
                        )
                    )
                    moveToNext()
                }
            }
        }
        return data
    }

    override suspend fun addTask(oldDate: String, task: List<TasksEntity>) {
        if(oldDate != "")
            tasks.deleteByDate(oldDate)
        task.forEach {
            tasks.insert(it)
        }
    }

    override suspend fun delete(task: List<TasksEntity>) {
        task.forEach {
            tasks.delete(it)
        }
    }


}