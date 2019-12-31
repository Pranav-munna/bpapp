package com.pranav.bpapp.controller

import com.pranav.bpapp.controller.responce.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AppInterphase {

    @FormUrlEncoded
    @POST("api/email/verify")
    fun postfunction(
        @Header("Accept") json: String,
        @Field("email") email: String
    ): Call<Response>

    @GET("api/posts")
    fun getfunction(
        @Header("Authorization") authKey: String,
        @Header("Accept") accept: String,
        @Query("search") search: String,
        @Query("page") page: Int
    ): Call<Response>

    @Multipart
    @POST("api/posts")
    fun postImagefunction(
        @Header("Authorization") authKey: String,
        @Header("Accept") accept: String,
        @Part("content") content: RequestBody,
        @Part("subject") subject: RequestBody,
        @Part imagePart: MultipartBody.Part?
    ): Call<Response>

}