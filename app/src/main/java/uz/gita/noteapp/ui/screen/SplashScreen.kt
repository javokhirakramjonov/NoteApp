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
import uz.gita.noteapp.databinding.ScreenSplashBinding
import uz.gita.noteapp.ui.viewModel.SplashScreenViewModel
import uz.gita.noteapp.ui.viewModel.viewModelImpl.SplashScreenViewModelImpl

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel: SplashScreenViewModel by viewModels<SplashScreenViewModelImpl>()
    private val binding by viewBinding(ScreenSplashBinding::bind)

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.goMainScreen()

        viewModel.goMainLiveData.observe(this@SplashScreen, goMainObserver)
    }

    private val goMainObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_mainScreen)
    }
}