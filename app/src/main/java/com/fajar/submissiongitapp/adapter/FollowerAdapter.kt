package com.fajar.submissiongitapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fajar.submissiongitapp.core.data.followers.FollowersResponse
import com.fajar.submissiongitapp.core.data.followers.FollowersResponseItem
import com.fajar.submissiongitapp.databinding.ItemUserListBinding

class FollowerAdapter(private val followersRes: FollowersResponse) :
    RecyclerView.Adapter<FollowerAdapter.FollowersViewHolder>() {
    var followerClickListener: ((FollowersResponseItem) -> Unit)? = null

    class FollowersViewHolder(val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        return FollowersViewHolder(
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return followersRes.size
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        val userFollowers = followersRes[position]
        holder.binding.itemImgAvatar.load(userFollowers.avatarUrl)
        holder.binding.itemTextUsername.text = userFollowers.login
        holder.itemView.setOnClickListener {
            followerClickListener?.invoke(userFollowers)
        }
    }
}