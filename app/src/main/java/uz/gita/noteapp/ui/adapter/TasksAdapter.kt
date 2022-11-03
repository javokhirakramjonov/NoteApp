package uz.gita.noteapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteapp.data.entity.TasksEntity
import uz.gita.noteapp.databinding.ItemTaskBinding

class TasksAdapter : ListAdapter<List<TasksEntity>, TasksAdapter.ViewHolder>(TaskEntityDiffUtil) {

    private var selectListener: ((item: List<TasksEntity>) -> Unit)? = null

    fun setSelectListener(block: (List<TasksEntity>) -> Unit) {
        selectListener = block
    }

    private object TaskEntityDiffUtil : DiffUtil.ItemCallback<List<TasksEntity>>() {
        override fun areItemsTheSame(oldItem: List<TasksEntity>, newItem: List<TasksEntity>): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: List<TasksEntity>, newItem: List<TasksEntity>): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.name.setOnClickListener {
                selectListener!!.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            if(getItem(absoluteAdapterPosition)[0].category == "")
                binding.name.text = "Not titled"
            else
                binding.name.text = getItem(absoluteAdapterPosition)[0].category
            binding.date.text = getItem(absoluteAdapterPosition)[0].date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksAdapter.ViewHolder {
        return ViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TasksAdapter.ViewHolder, position: Int) {
        holder.bind()
    }
}