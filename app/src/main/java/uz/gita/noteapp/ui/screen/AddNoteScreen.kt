package uz.gita.noteapp.ui.screen

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.wordpress.aztec.Aztec
import org.wordpress.aztec.ITextFormat
import org.wordpress.aztec.toolbar.IAztecToolbarClickListener
import uz.gita.noteapp.R
import uz.gita.noteapp.data.entity.NoteEntity
import uz.gita.noteapp.databinding.ScreenNoteEditorBinding
import uz.gita.noteapp.ui.viewModel.AddNoteScreenViewModel
import uz.gita.noteapp.ui.viewModel.viewModelImpl.AddNoteScreenViewModelImpl
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddNoteScreen : Fragment(R.layout.screen_note_editor) {
    private val viewModel: AddNoteScreenViewModel by viewModels<AddNoteScreenViewModelImpl>()
    private val binding by viewBinding(ScreenNoteEditorBinding::bind)

    @SuppressLint("SetTextI18n", "FragmentLiveDataObserve", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        Aztec.with(
            editor,
            tools,
            object : IAztecToolbarClickListener {
                override fun onToolbarCollapseButtonClicked() {}

                override fun onToolbarExpandButtonClicked() {}

                override fun onToolbarFormatButtonClicked(format: ITextFormat, isKeyboardShortcut: Boolean) {}

                override fun onToolbarHeadingButtonClicked() {}

                override fun onToolbarHtmlButtonClicked() {}

                override fun onToolbarListButtonClicked() {}

                override fun onToolbarMediaButtonClicked(): Boolean = false

            }
        )
        editor.setTextColor(Color.BLACK)

        val note = requireArguments().getSerializable("item") as NoteEntity

        if (note.date != "") {
            title.setText(note.name)
            editor.fromHtml(note.content)
        }

        save.setOnClickListener {
            note.content = editor.toFormattedHtml()
            note.name = title.text.toString()
            val formattedTime = SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().time)
            note.date = formattedTime
            viewModel.saveNote(note)
            viewModel.backToMain()
        }

        back.setOnClickListener {
            viewModel.backToMain()
        }

        viewModel.messageToUser.observe(this@AddNoteScreen, messageObserver)
        viewModel.backToMain.observe(this@AddNoteScreen, backToMainObserver)
    }

    private val messageObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }

    private val backToMainObserver = Observer<Unit> {
        findNavController().popBackStack()
    }
}