package com.eslam.callkt.internet


import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface ServerApi {

    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("udid") udid: String,
        @Field("email_phone") email: String,
        @Field("password") password: String,
        @Field("language") language: String
    ): Deferred<String>


}