package com.eslam.callkt.ui.home
import com.squareup.moshi.Json


data class ConfigResponse(
    @Json(name = "data") val data: ConfigResponseData?,
    @Json(name = "type") val type: String?
)

data class ConfigResponseData(
    @Json(name = "offline") val offline: String?,
    @Json(name = "online") val online: String?
)