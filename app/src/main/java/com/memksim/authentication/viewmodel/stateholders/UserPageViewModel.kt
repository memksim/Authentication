package com.memksim.authentication.viewmodel.stateholders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.authentication.model.User
import com.memksim.authentication.viewmodel.states.UserPageState

class UserPageViewModel: ViewModel() {

    private val _data: MutableLiveData<UserPageState> by lazy {
        MutableLiveData<UserPageState>()
    }
    var liveData: LiveData<UserPageState> = _data

    fun setState(user: User){
        _data.value = UserPageState(user)
    }

    fun getUsesList(): List<String>{
        val usesList: ArrayList<String> = arrayListOf()

        if(_data.value!!.user.google){
            usesList.add("Google")
        }
        if(_data.value!!.user.gov){
            usesList.add("ГосУслуги")
        }
        if(_data.value!!.user.vk){
            usesList.add("Vkontakte")
        }
        if(_data.value!!.user.fb){
            usesList.add("Facebook")
        }

        return usesList
    }

}