package com.fajar.submissiongitapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.submissiongitapp.R
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fajar.submissiongitapp.adapter.FollowerAdapter
import com.fajar.submissiongitapp.databinding.FragmentFollowersBinding
import com.fajar.submissiongitapp.ui.activity.DetailActivity
import com.fajar.submissiongitapp.ui.activity.DetailActivity.Companion.EXTRA_USERNAME_KEY
import com.fajar.submissiongitapp.ui.viewmodel.DetailViewModel
import com.fajar.submissiongitapp.ui.viewmodel.FollowerViewModel


class FollowersFragment : Fragment(R.layout.fragment_followers) {
    private val binding by viewBinding(FragmentFollowersBinding::bind)
    private val viewModel by viewModels<FollowerViewModel>()
    private val detailViewModel by activityViewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressFollower.isVisible = it
        }

        viewModel.errorMsg.observe(viewLifecycleOwner) { text ->
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }

        detailViewModel.username.observe(requireActivity()) { username ->
            viewModel.getFollowersUser(username)
        }

        viewModel.listFollower.observe(viewLifecycleOwner) {followerRes->
            val adapter = FollowerAdapter(followerRes)
            val layoutManager = LinearLayoutManager(requireActivity())
            binding.rvFollower.adapter = adapter
            binding.rvFollower.layoutManager = layoutManager
            adapter.followerClickListener = { followerResponseItem ->
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra(EXTRA_USERNAME_KEY, followerResponseItem.login)
                startActivity(intent)
            }
        }
    }
}
