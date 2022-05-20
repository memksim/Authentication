package com.memksim.authentication.viewmodel.states

data class RegSecondPageState(
    val name: String,
    val surname: String,
    val phone: String,
    val city: String = "none",
    val email: String,
    val password: String
)
