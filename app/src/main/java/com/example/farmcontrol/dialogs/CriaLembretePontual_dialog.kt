package com.example.farmcontrol.dialogs

import android.app.AlertDialog
import android.content.Context
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.farmcontrol.R
import com.example.farmcontrol.logica.Alarmes.AlarmeDAO
import com.example.farmcontrol.logica.Lembrete
import com.example.farmcontrol.logica.LembretesDao
import com.google.android.material.textfield.TextInputEditText

class CriaLembretePontual_dialog(var context: Context,var fragmentmanager:FragmentManager) {
    var dialog =AlertDialog.Builder(context).create()
    val inflater = LayoutInflater.from(context)

    val view = inflater.inflate(R.layout.alarme_pontual_dialog, null)
    val texto:TextView = view.findViewById(R.id.lembrete_pontual_hora)

    fun timer_picker(){
        var botão = view.findViewById<ImageButton>(R.id.lembrete_pontual_timepicker)
        botão.setOnClickListener {
            Timepicker_dialog(fragmentmanager, texto)
        }
    }
    fun confirmar(){
        var botão = view.findViewById<Button>(R.id.lembrete_pontual_confirmar)
        var nome_input = view.findViewById<TextInputEditText>(R.id.nome_novomedicamento)
        botão.setOnClickListener {

            var calendar = ScreenManager.calendar
            AlarmeDAO(context).criarAlarmePontual(calendar)
            LembretesDao().adiciona_lembrete(
                Lembrete(nome_input.text.toString(), 24.0,1, calendar),
                context
            )
            ScreenManager.atualiza_lista(context)
            dialog.dismiss()
        }
    }
    fun cancelar(){
        var botão = view.findViewById<Button>(R.id.lembrete_pontual_cancelar)
        botão.setOnClickListener {
            dialog.dismiss()
        }
        dialog.dismiss()
    }
    init {
        ScreenManager.calendar= Calendar.getInstance(TimeZone.getDefault())
        confirmar()
        cancelar()
        timer_picker()

        dialog.setView(view)
        dialog.show()
    }
}