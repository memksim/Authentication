package com.memksim.authentication.model

import androidx.room.*

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val uid: Int,

    @ColumnInfo(name = "_userName") val name: String,
    @ColumnInfo(name = "_userLastName") val surname: String,
    @ColumnInfo(name = "_phoneNumber") val phoneNumber: Int,
    @ColumnInfo(name = "_city") val city: String,
    @ColumnInfo(name = "_email") val email: String,
    @ColumnInfo(name = "_password") val password: String,

    @ColumnInfo(name = "_googleToken") val googleToken: String,
    @ColumnInfo(name = "_govToken") val govToken: String,
    @ColumnInfo(name = "_vkToken") val vkTokenL: String,
    @ColumnInfo(name = "_facebookToken") val fbToken: String,

    @ColumnInfo(name = "_uniqueUser") val unique: Boolean,
    @ColumnInfo(name = "_usesGoogle") val google: Boolean,
    @ColumnInfo(name = "_usesGov") val gov: Boolean,
    @ColumnInfo(name = "_usesVk") val vk: Boolean,
    @ColumnInfo(name = "_usesFacebook") val fb: Boolean,
)
