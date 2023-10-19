package com.example.farmcontrol

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.farmcontrol.logica.Services.BlockerService

class BlockerAct : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blocker)

        var botão_back = findViewById<Button>(R.id.backtomain)
        botão_back.setOnClickListener{
            BlockerService.block=true
            var main =  Intent(applicationContext, MainActivity::class.java)
            main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            applicationContext.startActivity(main)
        }
    }
}