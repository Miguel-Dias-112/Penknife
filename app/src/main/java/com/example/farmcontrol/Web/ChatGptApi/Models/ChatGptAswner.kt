package com.example.farmcontrol.Web.ChatGptApi.Models

import com.google.gson.annotations.SerializedName

data class ChatGptAswner(
    @SerializedName("model")
    var model:String,
    @SerializedName("choices")
    var choices:List<Choice>
)

