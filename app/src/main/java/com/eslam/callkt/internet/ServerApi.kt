package com.eslam.callkt.internet


import com.eslam.callkt.ui.ConfigResponse
import com.eslam.callkt.ui.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface ServerApi {

    @GET("index.php")
    fun getUserInfoAsync(
        @Query("type") type: String,
        @Query("do") number: String
        ): Deferred<UserResponse>


    @FormUrlEncoded
    @POST("index.php")
    fun getConfigAsync(
        @Field("mode") mode: String,
        @Field("type") type: String
    ): Deferred<ConfigResponse>


}