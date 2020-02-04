package com.eslam.callkt.ui

import com.squareup.moshi.Json


data class UserResponse(
    @Json(name = "data") val data: UserResponseData?,
    @Json(name = "type") val type: String?
)

data class UserResponseData(
    @Json(name = "title") val title: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "id") val id: String?,
    @Json(name = "mobile") val mobile: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "notes") val notes: String?


)