package com.memksim.authentication.viewmodel.stateholders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.authentication.model.User
import com.memksim.authentication.viewmodel.repos.DatabaseRepository
import com.memksim.authentication.viewmodel.states.AuthPageState

class AuthPageViewModel(app: Application): AndroidViewModel(app) {

    private val repository = DatabaseRepository(app)

    private val _data: MutableLiveData<AuthPageState> by lazy {
        MutableLiveData<AuthPageState>()
    }
    var liveData: LiveData<AuthPageState> = _data

    fun loadUsers(){
        _data.value = AuthPageState(
            _data.value?.email ?: "none",
            _data.value?.password ?: "none",
            repository.getUsersList()
        )
    }

    fun compareUsers(email: String): CompareResult{

        for(user in _data.value!!.users){
            if(email == user.email)
                return CompareResult(
                    true,
                    user
                )
        }

        return CompareResult(false, null)
    }




}

data class CompareResult(
    val result: Boolean,
    val user: User?
)