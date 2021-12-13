package com.example.postrequest



import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
   @POST("/test/")
   fun addData(@Body personData :UserDetails): Call<UserDetails>

   @GET("/test/")
   fun getUserData(): Call<List<UserDetails>>

   @PUT ("/test/{id}")
   fun updateUser(@Path("id")id:Int ,@Body personData :UserDetails): Call<UserDetails>


   @DELETE ("/test/{id}")

   fun deletUser (@Path("id")id:Int):Call<Void>








}