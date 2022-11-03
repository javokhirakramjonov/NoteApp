package uz.gita.noteapp.ui.adapter


import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteapp.data.entity.TasksEntity
import uz.gita.noteapp.databinding.ItemSubTasksBinding

class SubTaskAdapter : ListAdapter<TasksEntity, SubTaskAdapter.ViewHolder>(TaskDiffUtil) {

    private var editListener: ((index: Int, subTask: TasksEntity) -> Unit)? = null
    private var deleteSubTask: ((index: Int) -> Unit)? = null

    fun changeItem(index: Int, task: TasksEntity) {
        getItem(index).apply {
            content = task.content
            isDone = task.isDone
            notifyItemChanged(index)
        }
    }

    fun setEditListener(block: ((Int, TasksEntity) -> Unit)) {
        editListener = block
    }

    fun setDeleteListener(block: ((Int) -> Unit)) {
        deleteSubTask = block
    }

    private object TaskDiffUtil : DiffUtil.ItemCallback<TasksEntity>() {
        override fun areItemsTheSame(oldItem: TasksEntity, newItem: TasksEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TasksEntity, newItem: TasksEntity): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(private val binding: ItemSubTasksBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                edit.setOnClickListener {
                    editListener!!.invoke(absoluteAdapterPosition, getItem(absoluteAdapterPosition))
                }
                delete.setOnClickListener {
                    deleteSubTask!!.invoke(absoluteAdapterPosition)
                }
                task.setOnCheckedChangeListener { compoundButton, state ->
                    getItem(absoluteAdapterPosition).isDone =
                        kotlin.math.abs(1 - getItem(absoluteAdapterPosition).isDone)
                    if (getItem(absoluteAdapterPosition).isDone > 0)
                        task.paintFlags = task.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    else
                        task.paintFlags = task.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }
        }

        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                binding.task.isChecked = isDone == 1
                binding.task.text = content
            }
            binding.apply {
                getItem(absoluteAdapterPosition).isDone =
                    kotlin.math.abs(1 - getItem(absoluteAdapterPosition).isDone)
                if (getItem(absoluteAdapterPosition).isDone > 0)
                    task.paintFlags = task.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                else
                    task.paintFlags = task.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTaskAdapter.ViewHolder {
        return ViewHolder(ItemSubTasksBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SubTaskAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

}