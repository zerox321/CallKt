package com.eslam.callkt.internet


import com.eslam.callkt.ui.home.ConfigResponse
import com.eslam.callkt.ui.UserResponse
import com.eslam.callkt.ui.home.ClientListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface ServerApi {

    @GET("index.php")
    fun getUserInfoAsync(
        @Query("type") type: String,
        @Query("do") number: String
    ): Deferred<UserResponse>


    @GET("index.php")
    fun getClientsAsync(
        @Query("type") type: String
    ): Deferred<ClientListResponse>


    @FormUrlEncoded
    @POST("index.php")
    fun getConfigAsync(
        @Field("mode") mode: String,
        @Field("type") type: String
    ): Deferred<ConfigResponse>


}