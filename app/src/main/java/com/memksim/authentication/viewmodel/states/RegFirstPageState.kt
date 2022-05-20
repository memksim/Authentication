package com.memksim.authentication.viewmodel.states

data class RegFirstPageState(
    val name: String,
    val surname: String,
    val phone: String,
    val city: String = "none"
)
