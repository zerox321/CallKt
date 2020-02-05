package com.eslam.callkt.ui.home

import com.squareup.moshi.Json


data class ClientListResponse(
    @Json(name = "data") val data: List<ClientListResponseData?>?,
    @Json(name = "type") val type: String?
)

data class ClientListResponseData(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String?,
    @Json(name = "avatar") val avatar: String?,
    @Json(name = "mobile") val mobile: String?,
    @Json(name = "notes") val notes: String?
)