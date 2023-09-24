package com.fajar.submissiongitapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fajar.submissiongitapp.core.data.search.SearchUserResponse
import com.fajar.submissiongitapp.core.data.UserResponseItem
import com.fajar.submissiongitapp.databinding.ItemUserListBinding

class SearchAdapter (private val searchRes: SearchUserResponse) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){

    var searchUserClickListener: ((UserResponseItem) -> Unit)? = null

    class SearchViewHolder(val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return searchRes.items.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val userData = searchRes.items[position]
        holder.binding.itemImgAvatar.load(userData.avatarUrl)
        holder.binding.itemTextUsername.text = userData.login
        holder.itemView.setOnClickListener {
            searchUserClickListener?.invoke(userData)
        }
    }
}