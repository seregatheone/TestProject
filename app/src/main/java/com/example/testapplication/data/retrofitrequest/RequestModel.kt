package com.example.testapplication.data.retrofitrequest

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestModel(
    @SerializedName("userId")
    val userId:String,
    @SerializedName("id")
    val id:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("body")
    val body:String): Parcelable