package petros.efthymiou.groovy.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import petros.efthymiou.groovy.domain.DataSource
import petros.efthymiou.groovy.domain.PlayList
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val repositoy:DataSource): ViewModel() {

    private val _playLists:MutableLiveData<List<PlayList>> = MutableLiveData()

    val playList:LiveData<List<PlayList>>
            get() = _playLists



    init {
        viewModelScope.launch {
            repositoy.getPlayLists().collect {
                if (it.isSuccess)
                {
                    _playLists.value = it.getOrNull()!!
                }
                else
                {

                }
            }




        }
    }


}