package uz.gita.noteapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TasksEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var category: String,
    var content: String,
    var isDone: Int,

    var date: String
) : EditorEntity()