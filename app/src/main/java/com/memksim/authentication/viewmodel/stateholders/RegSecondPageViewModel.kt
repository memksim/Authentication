package com.memksim.authentication.viewmodel.stateholders

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.memksim.authentication.APP_TAG
import com.memksim.authentication.model.User
import com.memksim.authentication.viewmodel.repos.DatabaseRepository
import com.memksim.authentication.viewmodel.states.RegFirstPageState
import com.memksim.authentication.viewmodel.states.RegSecondPageState

class RegSecondPageViewModel(app: Application): AndroidViewModel(app) {

    private val repository = DatabaseRepository(app)

    private val _data: MutableLiveData<RegSecondPageState> by lazy{
        MutableLiveData<RegSecondPageState>()
    }
    var livedata: LiveData<RegSecondPageState> = _data

    fun setData(
        email: String,
        password: String
    ){
        _data.value = RegSecondPageState(
            _data.value!!.name,
            _data.value!!.surname,
            _data.value!!.phone,
            _data.value!!.city,
            email,
            password
        )
    }

    fun getStateFromFirstPage(state: RegFirstPageState){
        _data.value = RegSecondPageState(
            state.name,
            state.surname,
            state.phone,
            state.city,
            "none",
            "none"
        )
    }

    fun saveUser(){
        /*repository.saveUser(
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
        )*/

        Log.d(APP_TAG, "saveUser: ${User(
            0,
            _data.value!!.name,
            _data.value!!.surname,
            _data.value!!.phone,
            _data.value!!.city,
            _data.value!!.email,
            _data.value!!.password,
            unique = true
        )}")
    }

}