package com.fajar.submissiongitapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fajar.submissiongitapp.core.data.following.FollowingResponse
import com.fajar.submissiongitapp.core.data.following.FollowingResponseItem
import com.fajar.submissiongitapp.databinding.ItemUserListBinding

class FollowingAdapter(private val followingRes: FollowingResponse) :  RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>(){

    var followingItemClickListener: ((FollowingResponseItem) -> Unit)? = null

    class FollowingViewHolder(val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        return FollowingViewHolder(
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return followingRes.size
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val followingData = followingRes[position]
        holder.binding.itemImgAvatar.load(followingData.avatarUrl)
        holder.binding.itemTextUsername.text = followingData.login
        holder.itemView.setOnClickListener {
            followingItemClickListener?.invoke(followingData)
        }
    }
}