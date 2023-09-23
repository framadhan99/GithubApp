package com.fajar.submissiongitapp.core.data.search

import com.fajar.submissiongitapp.core.data.user.UserResponseItem
import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
	@SerializedName("incomplete_results")
	val incompleteResults: Boolean,
	@SerializedName("items")
	val items: ArrayList<UserResponseItem>,
	@SerializedName("total_count")
	val totalCount: Int
)