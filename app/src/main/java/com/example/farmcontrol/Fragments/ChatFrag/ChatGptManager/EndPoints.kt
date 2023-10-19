package com.example.farmcontrol.Fragments.ChatFrag.ChatGptManager

import com.example.farmcontrol.Fragments.ChatFrag.ClassesModelos.ChatGptAswner
import com.example.farmcontrol.Fragments.ChatFrag.ClassesModelos.Corpo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


class EndPoints {
    interface Endpoint {
        @POST("v1/chat/completions")
       //@POST("endpoint")
         fun pergunta_ao_chat(
            @Body body: Corpo,
            @Header("Content-Type") content_type: String?,
            @Header("Authorization") api: String?,

            ): Call<ChatGptAswner>

        //fun sendData(@Body requestBody: RequestBody?): Call<ResponseBody?>?
        @POST("endpoint")
        fun pergunta_ao_chat() : Call<ChatGptAswner>
    }



}