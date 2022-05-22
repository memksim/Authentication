package com.memksim.authentication.viewmodel.states

import com.memksim.authentication.model.User

data class AuthPageState(
    val email: String,
    val password: String,
    val users: List<User>
)
