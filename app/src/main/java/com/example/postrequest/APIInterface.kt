package com.example.postrequest



import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
   @POST("/test/")
   fun addData(@Body personData :UserDetails): Call<UserDetails>






}