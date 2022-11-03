package uz.gita.noteapp.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.data.entity.TasksEntity
import uz.gita.noteapp.databinding.PageTasksBinding
import uz.gita.noteapp.ui.adapter.TasksAdapter
import uz.gita.noteapp.ui.viewModel.TasksPageViewModel
import uz.gita.noteapp.ui.viewModel.viewModelImpl.TasksPageViewModelImpl
import java.util.ArrayList

@AndroidEntryPoint
class TasksPage : Fragment(R.layout.page_tasks) {
    private val viewModel: TasksPageViewModel by viewModels<TasksPageViewModelImpl>()
    private val binding by viewBinding(PageTasksBinding::bind)
    private val adapter = TasksAdapter()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        viewModel.loadTasks()

        adapter.apply {
            setSelectListener {
                viewModel.updateTasks(it)
            }
        }
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        viewModel.goEditorScreen.observe(this@TasksPage, goEditorObserver)
        viewModel.tasksLiveData.observe(this@TasksPage, tasksObserver)
    }

    private val goEditorObserver = Observer<List<TasksEntity>> {
        val item = Bundle()
        for(i in 1..it.size) {
            item.putSerializable("item$i", it[i - 1])
        }
        item.putInt("size", it.size)
        findNavController().navigate(R.id.action_mainScreen_to_addTasksScreen, item)
    }

    private val tasksObserver = Observer<List<List<TasksEntity>>> {
        adapter.submitList(it)
    }
}