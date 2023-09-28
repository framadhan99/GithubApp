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
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fajar.submissiongitapp.R
import com.fajar.submissiongitapp.adapter.FollowingAdapter
import com.fajar.submissiongitapp.databinding.FragmentFollowingBinding
import com.fajar.submissiongitapp.ui.activity.DetailActivity
import com.fajar.submissiongitapp.ui.activity.DetailActivity.Companion.EXTRA_USERNAME_KEY
import com.fajar.submissiongitapp.ui.viewmodel.DetailViewModel
import com.fajar.submissiongitapp.ui.viewmodel.FollowingViewModel

class FollowingFragment : Fragment(R.layout.fragment_following) {
    private val binding by viewBinding(FragmentFollowingBinding::bind)
    private val viewModel by activityViewModels<FollowingViewModel>()
    private val detailViewModel by activityViewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorMsg.observe(viewLifecycleOwner) { text ->
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }

        detailViewModel.username.observe(requireActivity()) { username ->
            viewModel.getUserFollowing(username)
        }

        viewModel.listFollowing.observe(viewLifecycleOwner) { followingResponse ->
            val adapter = FollowingAdapter(followingResponse)
            val layoutManager = LinearLayoutManager(requireActivity())
            binding.rvFollowing.adapter = adapter
            binding.rvFollowing.layoutManager = layoutManager
            adapter.followingItemClickListener = { followingResponseItem ->
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra(EXTRA_USERNAME_KEY, followingResponseItem.login)
                startActivity(intent)
            }
        }
    }
}