package uz.gita.noteapp.ui.viewModel

import androidx.lifecycle.LiveData

interface SplashScreenViewModel {
    val goMainLiveData: LiveData<Unit>

    fun goMainScreen()
}