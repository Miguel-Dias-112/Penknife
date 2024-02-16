package com.example.PenKnife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class AlarmeAtivadoActivity : AppCompatActivity() {
    override fun onResume() {
        super.onResume()
        titleColor=R.color.cor_de_letra
        window.statusBarColor=getColor(R.color.cor_de_letra)
        window
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarme_ativado)

        var nome_alarme = intent.getStringExtra("nome_alarme")
        var alarme = findViewById<TextView>(R.id.nomeAlarme)
        alarme.setText(nome_alarme)

        var btn=findViewById<MaterialButton>(R.id.materialButton)
        btn.setOnClickListener {
            var popupWindow =  Intent(this, MainActivity::class.java)
            startActivity(popupWindow)
        }
    }
}