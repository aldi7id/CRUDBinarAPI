package com.ajgroup.crudbinarapi


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("success")
    val success: Boolean
)