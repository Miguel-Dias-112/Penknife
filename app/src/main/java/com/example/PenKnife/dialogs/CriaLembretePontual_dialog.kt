package com.example.PenKnife.dialogs

import android.app.AlertDialog
import android.content.Context
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.PenKnife.R
import com.example.PenKnife.Telas.TelaLembretes.Lembretes.Lembrete
import com.example.PenKnife.Telas.TelaLembretes.Lembretes.LembretesDAO
import com.google.android.material.textfield.TextInputEditText

class CriaLembretePontual_dialog(var context: Context,var fragmentmanager:FragmentManager) {
    var dialog =AlertDialog.Builder(context).create()
    val inflater = LayoutInflater.from(context)

    val view = inflater.inflate(R.layout.alarme_pontual_dialog, null)
    val texto:TextView = view.findViewById(R.id.lembrete_pontual_hora)

    fun CriaBotãoTempo(){
        var botão = view.findViewById<ImageButton>(R.id.lembrete_pontual_timepicker)
        botão.setOnClickListener {
            Timepicker_dialog(fragmentmanager, texto)
        }
    }
    fun CriaBotãoConfirmar(){
        var botão = view.findViewById<Button>(R.id.lembrete_pontual_confirmar)
        var nome_input = view.findViewById<TextInputEditText>(R.id.nome_novomedicamento)
        botão.setOnClickListener {

            var calendar = ScreenManager.calendar.clone()

            LembretesDAO().adiciona_lembrete(
                Lembrete(nome_input.text.toString(), 24.0,1, calendar as Calendar,context), context
            )

            dialog.dismiss()
        }
    }
    fun CriaBotãoCancelar(){
        var botão = view.findViewById<Button>(R.id.lembrete_pontual_cancelar)
        botão.setOnClickListener {
            dialog.dismiss()
        }
        dialog.dismiss()
    }
    init {
        ScreenManager.calendar= Calendar.getInstance(TimeZone.getDefault())
        CriaBotãoConfirmar()
        CriaBotãoCancelar()
        CriaBotãoTempo()

        dialog.setView(view)
        dialog.show()
    }
}