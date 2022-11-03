package uz.gita.noteapp.ui.adapter


import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import uz.gita.noteapp.data.entity.NoteEntity
import uz.gita.noteapp.databinding.ItemNoteBinding

class NotesAdapter : ListAdapter<NoteEntity, NotesAdapter.ViewHolder>(NoteEntityDiffUtil) {

    var listenerSelect: ((item: NoteEntity) -> Unit)? = null

    fun setNoteSelectListener(listener: ((NoteEntity) -> Unit)) {
        listenerSelect = listener
    }

    private object NoteEntityDiffUtil : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.clicker.setOnClickListener {
                listenerSelect!!.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            binding.apply {
                if(getItem(absoluteAdapterPosition).name != "")
                    name.text = getItem(absoluteAdapterPosition).name
                else
                    name.text = "No title"
                content.fromHtml(getItem(absoluteAdapterPosition).content)
                date.text = getItem(absoluteAdapterPosition).date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        return ViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        holder.bind()
    }
}