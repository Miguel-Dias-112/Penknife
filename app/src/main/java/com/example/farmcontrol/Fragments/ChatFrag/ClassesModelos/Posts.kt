package com.example.farmcontrol.Fragments.ChatFrag.ClassesModelos

import com.google.gson.annotations.SerializedName


data class Posts(
    @SerializedName("userId")
    var userId : Int,
    @SerializedName("id")
    var id : Int,
    @SerializedName("title")
    var title : String,
    @SerializedName("body")
    var body : String
)
