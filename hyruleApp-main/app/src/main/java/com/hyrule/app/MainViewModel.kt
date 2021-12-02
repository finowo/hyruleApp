package com.hyrule.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hyrule.app.data.AppDatabase
import com.hyrule.app.data.HyruleEntity
import com.hyrule.app.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)

    val hyruleList = database?.entityDao()?.getAll()

    fun addSampleData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sampleEntities = SampleDataProvider.getHyruleEntities()
                database?.entityDao()?.insertALl(sampleEntities)
            }
        }
    }

    fun deleteEntities(selectedEntities: List<HyruleEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
               database?.entityDao()?.deleteEntities(selectedEntities)
            }
        }
    }

    fun deleteAllEntities() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.entityDao()?.deleteAll()
            }
        }
    }
}