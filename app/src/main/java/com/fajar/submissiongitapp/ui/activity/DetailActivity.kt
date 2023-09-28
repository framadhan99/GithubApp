package com.fajar.submissiongitapp.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.fajar.submissiongitapp.R
import com.fajar.submissiongitapp.adapter.SetSectionPageAdapter
import com.fajar.submissiongitapp.core.room.entity.UserFav
import com.fajar.submissiongitapp.databinding.ActivityDetailBinding
import com.fajar.submissiongitapp.ui.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity(R.layout.activity_detail) {

    private val binding by viewBinding(ActivityDetailBinding::bind)
    private val detailViewModel by viewModels<DetailViewModel>()

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_follower,
            R.string.tab_following
        )
        const val EXTRA_USERNAME_KEY = "username_key"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.detailToolbar)
        val username = intent?.getStringExtra(EXTRA_USERNAME_KEY) ?: ""

        detailViewModel.setUsername(username)

        detailViewModel.isLoading.observe(this) {
            binding.progressbarDetail.isVisible = it
        }

        detailViewModel.errorMsg.observe(this) { text ->
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }

        setUserData()

        detailViewModel.getDetailUser(username)

        setSectionPage()

        binding.detailToolbar.setNavigationOnClickListener { finish() }
    }

    private fun setSectionPage() {
        val sectionPageAdapter = SetSectionPageAdapter(this)
        binding.viewPager2.adapter = sectionPageAdapter
        TabLayoutMediator(binding.detailTabLayout, binding.viewPager2) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
    @SuppressLint("StringFormatMatches")
    private fun setUserData() {
        detailViewModel.user.observe(this) { detailRes ->
            binding.detailImgAvatar.load(detailRes.avatarUrl)
            binding.detailTextUsername.text = detailRes.login
            binding.detailTextName.text = detailRes.name
            binding.textFollowerSize.text =
                getString(R.string.follower_count, detailRes.followers)
            binding.textFollowingSize.text =
                getString(R.string.following_count, detailRes.following)
            supportActionBar?.title = detailRes.login
            val userFavorite = UserFav(detailRes.login, detailRes.avatarUrl)
            insertOrDeleteUserFavorite(userFavorite)
            detailViewModel.getUserFavorite(detailRes.login)
            detailViewModel.userFavorite.observe(this) {
                if(it == null) {
                    binding.fabAddToFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                } else {
                    binding.fabAddToFavorite.setImageResource(R.drawable.baseline_favorite_24)
                }
            }

        }
    }

    private fun insertOrDeleteUserFavorite(userFavorite : UserFav) {
        binding.fabAddToFavorite.setOnClickListener {
            detailViewModel.insertOrDeleteUserToFavorite(userFavorite) {
                binding.fabAddToFavorite.setImageResource(it)
            }
        }
    }
}