package petros.efthymiou.groovy.ui.fragments

import android.util.Log
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
import petros.efthymiou.groovy.domain.Result
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val repositoy:DataSource): ViewModel() {

    private val _playLists:MutableLiveData<List<PlayList>> = MutableLiveData()

    val playList:LiveData<List<PlayList>>
            get() = _playLists

    private val _errorState:MutableLiveData<String> = MutableLiveData()

    val errorState:LiveData<String>
        get() = _errorState



    suspend fun getPlayLists(){

        viewModelScope.launch {
            repositoy.getPlayLists().collect {
                if (it is Result.Success)
                {
                    _playLists.postValue(it.data)
                    Log.e(null, "getPlayLists: success ", )
                }
                else if (it is Result.Error)
                {
                    _errorState.postValue(it.message)

                    Log.e(null, "getPlayLists: ${it.message}", )



                }
            }
        }



    }



}