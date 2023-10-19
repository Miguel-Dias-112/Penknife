package com.example.farmcontrol.Fragments.ChatFrag.ClassesModelos

import com.google.gson.annotations.SerializedName

data class ChatGptAswner(
    @SerializedName("model")
    var model:String,
    @SerializedName("choices")
    var choices:List<Choice>
)

