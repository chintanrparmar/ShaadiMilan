package com.crp.shaadimilan.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.crp.shaadimilan.databinding.ActivityMainBinding
import com.crp.shaadimilan.model.State
import com.crp.shaadimilan.model.UserModel
import com.crp.shaadimilan.ui.adapter.UserAdapter
import com.crp.shaadimilan.utils.NetworkHelper
import com.crp.shaadimilan.viewmodel.UserViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModel()
    private val networkHelper: NetworkHelper by inject()
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setUpObserver()
        networkState()
        fetchData()
    }

    private fun networkState() {
        if (!networkHelper.isNetworkConnected()) {
            Toast.makeText(this, "Showing offline data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpObserver() {
        userViewModel.userResponse.observe(this, Observer {
            when (it) {
                is State.Success -> {
                    renderList(it.data)
                }
                is State.Error -> {
                    Log.e("Sata", it.message)
                }
                is State.Loading -> {
                    Log.e("Sata", "Loading")
                }
            }
        })
    }

    private fun fetchData() {
        userViewModel.getUsers()
    }

    private fun renderList(list: List<UserModel>) {
        activityMainBinding.userRv.adapter = UserAdapter(list.filter { it.inviteStatus != 2 }) {
            updateInviteState(it)
        }
    }

    private fun updateInviteState(userModel: UserModel) {
        userViewModel.updateData(userModel)
    }
}