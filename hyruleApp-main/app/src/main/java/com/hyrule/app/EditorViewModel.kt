package com.hyrule.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hyrule.app.data.AppDatabase
import com.hyrule.app.data.HyruleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditorViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)
    val currentEntity = MutableLiveData<HyruleEntity>()

    fun getEntityById(entityId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val entity =
                    if (entityId != NEW_HYRULE_ID) {
                        database?.entityDao()?.getEntityById(entityId)
                    } else {
                        HyruleEntity()
                    }
                currentEntity.postValue(entity)
            }
        }
    }

    fun updateEntity() {
        currentEntity.value?.let {
            it.text = it.text.trim()
            if (it.id == NEW_HYRULE_ID && it.text.isEmpty()) {
                return
            }
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    if(it.text.isEmpty()){
                        database?.entityDao()?.deleteEntity(it)
                    } else {
                        database?.entityDao()?.insertEntity(it)
                    }
                }
            }
        }
    }

}