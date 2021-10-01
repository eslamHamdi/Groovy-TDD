package petros.efthymiou.groovy.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import petros.efthymiou.groovy.domain.PlayList

class MainViewModel: ViewModel() {

    private val _playLists:MutableLiveData<Result<List<PlayList>>> = MutableLiveData()

    val playList:LiveData<Result<List<PlayList>>>
            get() = _playLists




}