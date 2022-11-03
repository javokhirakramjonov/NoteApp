package uz.gita.noteapp.ui.viewModel.viewModelImpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.noteapp.ui.viewModel.SplashScreenViewModel

class SplashScreenViewModelImpl : ViewModel(), SplashScreenViewModel {
    override val goMainLiveData = MutableLiveData<Unit>()

    override fun goMainScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            goMainLiveData.postValue(Unit)
        }
    }
}