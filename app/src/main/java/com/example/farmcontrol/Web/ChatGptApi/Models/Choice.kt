package com.example.farmcontrol.Web.ChatGptApi.Models

data class Choice(
    val message: Message,
    val finish_reason:String,
    val index:Int
)