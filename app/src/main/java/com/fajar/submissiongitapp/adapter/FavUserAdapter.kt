package com.fajar.submissiongitapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fajar.submissiongitapp.core.room.entity.UserFav
import com.fajar.submissiongitapp.databinding.ItemUserListBinding

class FavUserAdapter(private val userList: List<UserFav>) :
    RecyclerView.Adapter<FavUserAdapter.FavoriteUserViewHolder>() {

    var favoriteClickListener: ((UserFav) -> Unit)? = null

    class FavoriteUserViewHolder(val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
        return FavoriteUserViewHolder(
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) {
        val data = userList[position]
        holder.binding.itemImgAvatar.load(data.avatarUrl)
        holder.binding.itemTextUsername.text = data.username
        holder.itemView.setOnClickListener {
            favoriteClickListener?.invoke(data)
        }
    }
}