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
                            "${it.name.first} ${it.name.last}",
                            it.picture.medium, it.dob.age.toString(),
                            it.gender,
                            it.location.city,
                            it.location.country, 0
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

    fun updateInvite(userModel: UserModel) = flow {

        try {
            val resValue = updateDB(userModel)
            if (resValue == 1) {
                emit(resValue)
            } else {
                emit(0)
            }
        } catch (e: Exception) {
            emit(2)
        }

    }.flowOn(Dispatchers.IO)


    private fun saveRemoteData(userList: List<UserModel>) = userDao.insertUserList(userList)


    private suspend fun fetchFromAPI(): UserResponse = userAPI.getUserResponse("10")

    private fun fetchFromDB() = userDao.getAllUsers()

    private fun updateDB(userModel: UserModel) = userDao.updateStatus(userModel)
}