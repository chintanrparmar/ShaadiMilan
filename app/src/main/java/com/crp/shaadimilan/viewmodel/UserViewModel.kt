package com.crp.shaadimilan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crp.shaadimilan.database.UserRepository
import com.crp.shaadimilan.model.UserModel
import com.crp.shaadimilan.model.State
import com.crp.shaadimilan.model.UserResponse
import com.crp.shaadimilan.utils.NetworkHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class UserViewModel(
    private val userRepository: UserRepository
) :
    KoinComponent, ViewModel() {

    private val _userResponse = MutableLiveData<State<List<UserModel>>>()
    val userResponse: LiveData<State<List<UserModel>>>
        get() = _userResponse

    fun getUsers() {
        viewModelScope.launch {
            userRepository.getAllUsers().collect {
                _userResponse.value = it
            }
        }
    }

    fun updateData(userModel: UserModel) {
        viewModelScope.launch {
            userRepository.updateInvite(userModel).collect {
                if (it == 1) {
                    Log.e("State", "Success")
                } else {
                    Log.e("State", "Fail")
                }
            }
        }
    }
}