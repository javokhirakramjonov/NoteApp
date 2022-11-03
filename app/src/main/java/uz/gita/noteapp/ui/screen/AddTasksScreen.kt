package uz.gita.noteapp.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.data.entity.TasksEntity
import uz.gita.noteapp.databinding.ScreenTasksEditorBinding
import uz.gita.noteapp.ui.adapter.SubTaskAdapter
import uz.gita.noteapp.ui.viewModel.AddTasksScreenViewModel
import uz.gita.noteapp.ui.viewModel.viewModelImpl.AddTasksScreenViewModelImpl
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class AddTasksScreen : Fragment(R.layout.screen_tasks_editor) {
    private val viewModel: AddTasksScreenViewModel by viewModels<AddTasksScreenViewModelImpl>()
    private val binding by viewBinding(ScreenTasksEditorBinding::bind)
    private val adapter = SubTaskAdapter()

    @SuppressLint("SetTextI18n", "FragmentLiveDataObserve", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        var myIndex: Int = 0
        var myTask: TasksEntity? = null
        var oldDate: String = ""
        addTask.text = "Add"

        save.setOnClickListener {
            val formattedTime = SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().time)
            viewModel.saveTasks(oldDate, formattedTime, title.text.toString(), adapter.currentList)
        }

        addTask.setOnClickListener {
            if(addTask.text == "Add") {
                if (task.text.isNotEmpty()) {
                    viewModel.addSubTask(TasksEntity(0, "", task.text.toString(), 0, ""))
                    task.setText("")
                }
            } else {
                addTask.text = "Add"
                myTask!!.content = task.text.toString()
                task.setText("")
                viewModel.changeTask(myIndex, myTask!!)
            }
        }

        val tasks = ArrayList<TasksEntity>()
        requireArguments().apply {
            if (getSerializable("size") != null) {
                oldDate = (getSerializable("item1") as TasksEntity).date
                for (i in 1..(getSerializable("size") as Int)) {
                    tasks.add(getSerializable("item$i") as TasksEntity)
                }
            }
        }

        if(tasks.isNotEmpty())
            title.setText(tasks[0].category)

        subTasks.adapter = adapter
        subTasks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.submitList(tasks)

        adapter.setEditListener { indexT, task ->
            binding.addTask.text = "Update subtask"
            binding.task.setText(task.content)
            binding.task.setSelection(binding.task.length())
            myIndex = indexT
            myTask = task
        }
        adapter.setDeleteListener { index ->
            viewModel.deleteTask(index)
        }

        back.setOnClickListener {
            viewModel.backToMain()
        }


        viewModel.changeTaskLiveData.observe(this@AddTasksScreen, changeTaskObserver)
        viewModel.deleteTaskLiveData.observe(this@AddTasksScreen, deleteTaskObserver)
        viewModel.task.observe(this@AddTasksScreen, taskObserver)
        viewModel.messageToUser.observe(this@AddTasksScreen, messageObserver)
        viewModel.backToMain.observe(this@AddTasksScreen, backToMainObserver)
    }

    @SuppressLint("SetTextI18n")
    private val changeTaskObserver = Observer<Pair<Int, TasksEntity>> {
        adapter.changeItem(it.first, it.second)
    }

    private val deleteTaskObserver = Observer<Int> {
        val data = ArrayList<TasksEntity>()
        data.addAll(adapter.currentList)
        data.removeAt(it)
        adapter.submitList(data)
    }

    private val taskObserver = Observer<TasksEntity> {
        val temp = ArrayList<TasksEntity>()
        temp.addAll(adapter.currentList)
        temp.add(it)

        adapter.submitList(temp)
        adapter.notifyItemInserted(temp.size - 1)
    }

    private val messageObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }

    private val backToMainObserver = Observer<Unit> {
        findNavController().popBackStack()
    }
}