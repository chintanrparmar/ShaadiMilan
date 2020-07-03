package com.crp.shaadimilan.database

import com.crp.shaadimilan.api.UserAPIService
import com.crp.shaadimilan.model.UserModel
import com.crp.shaadimilan.model.State
import com.crp.shaadimilan.model.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class UserRepository(private val userDao: UserDao, private val userAPI: UserAPIService) {


    fun getAllUsers() = flow<State<List<UserModel>>> {

        emit(State.loading())

        try {
            val apiResponse = fetchFromAPI()

            if (apiResponse.results.isNotEmpty()) {
                val list = ArrayList<UserModel>()
                apiResponse.results.forEach {
                    list.add(
                        UserModel(
                            it.login.uuid,
                            it.dob.age.toString(),
                            it.gender,
                            it.location.city,
                            "${it.name.first} ${it.name.last}"
                        )
                    )
                }
                saveRemoteData(list)
            } else {
                emit(State.error("No Data Found!"))
            }
        } catch (e: Exception) {

            emit(State.error("Something went wrong!"))

        }

        emitAll(fetchFromDB().map {
            State.success(it)
        })

    }.flowOn(Dispatchers.IO)


    private fun saveRemoteData(userList: List<UserModel>) = userDao.insertUserList(userList)


    private suspend fun fetchFromAPI(): UserResponse = userAPI.getUserResponse("10")

    private fun fetchFromDB() = userDao.getAllUsers()
}