package com.crp.shaadimilan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.crp.shaadimilan.model.State
import com.crp.shaadimilan.utils.NetworkHelper
import com.crp.shaadimilan.viewmodel.UserViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModel()
    private val networkHelper: NetworkHelper by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                    Log.e("Data", it.data.toString())
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
}