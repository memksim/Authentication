package com.memksim.authentication.viewmodel.states

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegFirstPageState(
    val name: String,
    val surname: String,
    val phone: String,
    val city: String
): Parcelable
