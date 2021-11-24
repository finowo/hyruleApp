package com.hyrule.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hyrule.app.data.HyruleEntity
import com.hyrule.app.data.SampleDataProvider

class MainViewModel : ViewModel() {

    val hyruleList = MutableLiveData<List<HyruleEntity>>()

    init{
        hyruleList.value = SampleDataProvider.getHyruleEntities()
    }

}