package com.fajar.submissiongitapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fajar.submissiongitapp.R
import com.fajar.submissiongitapp.adapter.FavUserAdapter
import com.fajar.submissiongitapp.databinding.ActivityFavoriteBinding
import com.fajar.submissiongitapp.ui.viewmodel.FavoriteViewModel

class FavoriteActivity :  AppCompatActivity(R.layout.activity_favorite) {
    private val binding by viewBinding(ActivityFavoriteBinding::bind)
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.favoriteToolbar)

        favoriteViewModel.userFav.observe(this) { userFavorite ->
            val adapter = FavUserAdapter(userFavorite)
            binding.rvFavoriteUser.adapter = adapter
            binding.rvFavoriteUser.layoutManager = LinearLayoutManager(this)
            adapter.favoriteClickListener = { userFavorite ->
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USERNAME_KEY, userFavorite.username)
                startActivity(intent)
            }
        }

        binding.favoriteToolbar.setNavigationOnClickListener { finish() }
    }
}