package com.fajar.submissiongitapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fajar.submissiongitapp.R
import com.fajar.submissiongitapp.adapter.SearchAdapter
import com.fajar.submissiongitapp.adapter.UserListAdapter
import com.fajar.submissiongitapp.databinding.ActivityMainBinding
import com.fajar.submissiongitapp.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val mainViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.isLoading.observe(this) {
            binding.progressbarMain.isVisible = it
        }

        mainViewModel.errorMsg.observe(this) { text ->
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }

        mainViewModel.listUser.observe(this) { userResponse ->
            val adapter = UserListAdapter(userResponse)
            val layoutManager = LinearLayoutManager(this)
            binding.rvListUsers.adapter = adapter
            binding.rvListUsers.layoutManager = layoutManager
            adapter.userClickListener = { user ->
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USERNAME_KEY, user.login)
                startActivity(intent)
            }
        }

        setSearchbar()
        setSearchbarMenu()
    }

    private fun setSearchbarMenu() {
        with(binding) {
            searchView.setupWithSearchBar(binding.searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                val query = searchView.editText.text
                mainViewModel.searchUser(query.toString())
                false
            }
        }
        mainViewModel.searchUser.observe(this) { searchRes ->
            val adapter = SearchAdapter(searchRes)
            val layoutManager = LinearLayoutManager(this)
            binding.rvSearchUser.adapter = adapter
            binding.rvSearchUser.layoutManager = layoutManager
            adapter.searchUserClickListener = { user ->
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USERNAME_KEY, user.login)
                startActivity(intent)
            }
        }
    }

    private fun setSearchbar() {
        with(binding) {
            searchView.setupWithSearchBar(binding.searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                val query = searchView.editText.text
                mainViewModel.searchUser(query.toString())
                false
            }
        }
    }
}