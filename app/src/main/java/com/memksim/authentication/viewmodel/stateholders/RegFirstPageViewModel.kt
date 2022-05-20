package com.memksim.authentication.viewmodel.stateholders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.authentication.viewmodel.repos.DatabaseRepository
import com.memksim.authentication.viewmodel.states.RegFirstPageState

class RegFirstPageViewModel: ViewModel() {

    private val _data: MutableLiveData<RegFirstPageState> by lazy{
        MutableLiveData<RegFirstPageState>()
    }
    var livedata: LiveData<RegFirstPageState> = _data

    fun setData(name: String, surname: String, phone: String, city: String){
        _data.value = RegFirstPageState(
            name,
            surname,
            phone,
            city
        )
    }

}