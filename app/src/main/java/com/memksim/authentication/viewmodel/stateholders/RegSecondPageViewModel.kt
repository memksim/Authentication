package com.memksim.authentication.viewmodel.stateholders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.memksim.authentication.model.User
import com.memksim.authentication.viewmodel.repos.DatabaseRepository
import com.memksim.authentication.viewmodel.states.RegSecondPageState

class RegSecondPageViewModel(app: Application): AndroidViewModel(app) {

    private val repository = DatabaseRepository(app)

    private val _data: MutableLiveData<RegSecondPageState> by lazy{
        MutableLiveData<RegSecondPageState>()
    }
    var livedata: LiveData<RegSecondPageState> = _data

    fun setData(
        name: String,
        surname: String,
        phone: String,
        city: String,
        email: String,
        password: String
    ){
        _data.value = RegSecondPageState(
            name,
            surname,
            phone,
            city,
            email,
            password
        )
    }

    fun saveUser(){
        repository.saveUser(
            User(
                0,
                _data.value!!.name,
                _data.value!!.surname,
                _data.value!!.phone,
                _data.value!!.city,
                _data.value!!.email,
                _data.value!!.password,
                unique = true
            )
        )
    }

}