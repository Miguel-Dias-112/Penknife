package com.example.farmcontrol.Web.ChatGptApi.Models

data class Corpo(
    val model:String,
    val messages: List<MessageBody>,
    val temperature:Double

)