package com.example.farmcontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AlarmeAtivadoAct : AppCompatActivity() {
    override fun onResume() {
        super.onResume()
        titleColor=R.color.cor_de_letra
        window.statusBarColor=getColor(R.color.cor_de_letra)
        window
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarme_ativado)
    }
}