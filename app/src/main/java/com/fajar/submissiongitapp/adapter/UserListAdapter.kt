package com.fajar.submissiongitapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fajar.submissiongitapp.core.data.UserResponse
import com.fajar.submissiongitapp.core.data.UserResponseItem
import com.fajar.submissiongitapp.databinding.ItemUserListBinding

class UserListAdapter(private val userRes : UserResponse) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    var userClickListener: ((UserResponseItem) -> Unit)? = null

    class UserViewHolder(val binding: ItemUserListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
    override fun getItemCount(): Int {
        return userRes.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userData = userRes[position]
        holder.binding.itemImgAvatar.load(userData.avatarUrl)
        holder.binding.itemTextUsername.text = userData.login
        holder.itemView.setOnClickListener {
            userClickListener?.invoke(userData)
        }
    }
}