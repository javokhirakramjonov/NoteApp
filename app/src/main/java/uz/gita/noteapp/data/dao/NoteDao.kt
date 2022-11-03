package uz.gita.noteapp.data.dao

import android.database.Cursor
import androidx.room.*
import uz.gita.noteapp.data.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity ORDER BY date DESC")
    fun getNotes(): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)
}