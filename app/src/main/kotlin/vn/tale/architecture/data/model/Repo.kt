package vn.tale.architecture.data.model

import vn.tale.architecture.data.entity.UserResponse
/**
 * Created by Giang Nguyen on 1/14/17.
 */
data class Repo(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String,
    val htmlUrl: String,
    val stargazersCount: Int,
    val forksCount: Int,
    val language: String,
    val owner: UserResponse
)