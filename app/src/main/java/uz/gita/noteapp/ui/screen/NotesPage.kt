package uz.gita.noteapp.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.data.entity.NoteEntity
import uz.gita.noteapp.databinding.PageNotesBinding
import uz.gita.noteapp.ui.adapter.NotesAdapter
import uz.gita.noteapp.ui.viewModel.NotesPageViewModel
import uz.gita.noteapp.ui.viewModel.viewModelImpl.NotesPageViewModelImpl

@AndroidEntryPoint
class NotesPage : Fragment(R.layout.page_notes) {
    private val viewModel: NotesPageViewModel by viewModels<NotesPageViewModelImpl>()
    private val binding by viewBinding(PageNotesBinding::bind)
    private val adapter = NotesAdapter()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        recycler.adapter = adapter
//        recycler.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        recycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter.setNoteSelectListener {
            viewModel.updateNote(it)
        }

        viewModel.loadNotes()
        viewModel.goEditorScreen.observe(this@NotesPage, goEditorObserver)
        viewModel.notesLiveData.observe(this@NotesPage, notesObserver)
    }

    private val goEditorObserver = Observer<NoteEntity> {
        val note = Bundle()
        note.putSerializable("item", it)
        findNavController().navigate(R.id.action_mainScreen_to_addNoteScreen, note)
    }

    private val notesObserver = Observer<List<NoteEntity>> {
        adapter.submitList(it)
    }

}