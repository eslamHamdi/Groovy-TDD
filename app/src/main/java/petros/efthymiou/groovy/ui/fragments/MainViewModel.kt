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
import petros.efthymiou.groovy.domain.DomainListDetails
import petros.efthymiou.groovy.domain.PlayList
import petros.efthymiou.groovy.domain.Result
import petros.efthymiou.groovy.utils.SingleLiveEvent
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val repositoy: DataSource) : ViewModel() {

    private val _playLists: MutableLiveData<List<PlayList>> = MutableLiveData()

    val playList: LiveData<List<PlayList>>
        get() = _playLists

    private val _errorState: SingleLiveEvent<String> = SingleLiveEvent()

    val errorState: SingleLiveEvent<String>
        get() = _errorState

    private var _progressLiveData: MutableLiveData<Boolean> = MutableLiveData(true)

    val progressLiveData: LiveData<Boolean>
        get() = _progressLiveData

    private var _progressDetailsLiveData: MutableLiveData<Boolean> = MutableLiveData(true)
    val progressDetailsLiveData: LiveData<Boolean>
        get() = _progressDetailsLiveData


    private val _playListDetails: MutableLiveData<DomainListDetails> = MutableLiveData()

    val playListDetails: LiveData<DomainListDetails>
        get() = _playListDetails


    suspend fun getPlayLists() {
        viewModelScope.launch {

            repositoy.getPlayLists().collect {
                if (it is Result.Success) {
                    _playLists.value = (it.data)
                    _progressLiveData.postValue(false)

                    Log.e(null, "getPlayLists: success ")
                } else if (it is Result.Error) {
                    _errorState.value = (it.message)
                    _progressLiveData.postValue(false)

                    Log.e(null, "getPlayLists: ${it.message}")


                } else if (it is Result.Loading) {
                    _progressLiveData.postValue(true)
                }
            }


        }


    }

    suspend fun getPlayListsDetails(id: String) {

        viewModelScope.launch {
            repositoy.getListDetails(id).collect {

                when (it) {
                    is Result.Success -> {

                        _playListDetails.value = (it.data)
                        _progressDetailsLiveData.value = false
                    }
                    is Result.Error -> {
                        _errorState.value = (it.message)
                        _progressDetailsLiveData.value = false
                    }
                    else -> {
                        _progressDetailsLiveData.value = true
                    }
                }

            }
        }

    }


}