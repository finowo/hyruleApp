package com.hyrule.app

import android.util.Log
import androidx.lifecycle.*
import com.hyrule.app.data.HyruleEntity
import com.hyrule.app.dataaccess.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _entities: MutableLiveData<List<HyruleEntity>> = MutableLiveData()

    val entities: LiveData<List<HyruleEntity>>
        get() = _entities

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    init {
        getEntities()
    }

    private fun getEntities() {
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedEntities = RetrofitInstance.api.getHyruleEntities()
            Log.i(TAG, "List of Plants : $fetchedEntities")
            _entities.value = fetchedEntities
            _isLoading.value = false
        }
    }
}