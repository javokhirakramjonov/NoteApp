package uz.gita.noteapp.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.data.entity.EditorEntity
import uz.gita.noteapp.data.entity.NoteEntity
import uz.gita.noteapp.data.entity.TasksEntity
import uz.gita.noteapp.databinding.ScreenMainBinding
import uz.gita.noteapp.ui.adapter.PagerAdapter
import uz.gita.noteapp.ui.viewModel.MainScreenViewModel
import uz.gita.noteapp.ui.viewModel.viewModelImpl.MainScreenViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()
    private val binding by viewBinding(ScreenMainBinding::bind)

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        val adapter = PagerAdapter(childFragmentManager, lifecycle)
        add.setOnClickListener {
            val item: EditorEntity = if (pager.currentItem == 0)
                NoteEntity(0, "", "", "")
            else
                TasksEntity(0, "", "",  0, "")
            item.page = pager.currentItem
            viewModel.openEditor(item)
        }

        bottomBar.apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.notes -> {
                        viewModel.openPage(0)
                        true
                    }
                    else -> {
                        viewModel.openPage(1)
                        true
                    }
                }
            }
        }

        pager.adapter = adapter
        pager.isUserInputEnabled = false

        viewModel.editorLiveData.observe(this@MainScreen, openEditorObserver)
        viewModel.pageLiveData.observe(this@MainScreen, openPageObserver)
    }

    private val openEditorObserver = Observer<EditorEntity> {
        val item = Bundle()
        item.putSerializable("item", it)

        if (it.page == 0)
            findNavController().navigate(R.id.action_mainScreen_to_addNoteScreen, item)
        else
            findNavController().navigate(R.id.action_mainScreen_to_addTasksScreen, item)
    }

    private val openPageObserver = Observer<Int> {
        binding.pager.currentItem = it
    }
}