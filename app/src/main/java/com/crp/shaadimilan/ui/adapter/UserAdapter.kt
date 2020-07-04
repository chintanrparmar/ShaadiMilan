package com.crp.shaadimilan.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.crp.shaadimilan.databinding.UserInviteItemBinding
import com.crp.shaadimilan.model.UserModel

class UserAdapter(private val list: List<UserModel>, val adapterOnClick: (UserModel) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserView>() {

    inner class UserView(private val binding: UserInviteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userModel: UserModel) {
            binding.nameTv.text = userModel.name
            binding.genderTv.text = userModel.gender
            binding.ageTv.text = "${userModel.age} Years"
            binding.placeTv.text = "${userModel.city}, ${userModel.country}"
            binding.profileIv.load(userModel.profilePic) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }

            binding.acceptBt.setOnClickListener {
                userModel.inviteStatus = 1
                adapterOnClick(userModel)
            }
            binding.declineBt.setOnClickListener {
                userModel.inviteStatus = 2
                adapterOnClick(userModel)
            }

            if (userModel.inviteStatus != 0) {
                binding.acceptBt.visibility = View.GONE
                binding.declineBt.visibility = View.GONE
                binding.inviteTv.visibility = View.VISIBLE
            } else {
                binding.acceptBt.visibility = View.VISIBLE
                binding.declineBt.visibility = View.VISIBLE
                binding.inviteTv.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserView(
        UserInviteItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserView, position: Int) = holder.bind(list[position])

}