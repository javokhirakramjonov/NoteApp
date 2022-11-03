package uz.gita.noteapp.data.dao

import android.database.Cursor
import android.os.FileObserver.DELETE
import androidx.room.*
import uz.gita.noteapp.data.entity.TasksEntity

@Dao
interface TasksDao {
    @Query("SELECT * FROM TasksEntity ORDER BY category")
    fun getTasks(): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: TasksEntity)

    @Delete
    fun delete(task: TasksEntity)

    @Query("DELETE FROM TasksEntity WHERE date=:date")
    fun deleteByDate(date: String)
}