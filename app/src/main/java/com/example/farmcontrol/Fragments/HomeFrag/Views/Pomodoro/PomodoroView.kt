package com.example.farmcontrol.Fragments.HomeFrag.Views.Pomodoro

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.farmcontrol.R
import com.example.farmcontrol.logica.Services.PomodoroService

class PomodoroView(var view: View, var c: Context) {

    private fun checa_valor(valor: CharSequence, valor_padrão:Long): Long {
        var valor = valor.toString()

        if(valor==""){
            return (valor_padrão)

        }else{
            if (valor_padrão==5L){
                return (valor.toInt()).toLong()
            }
            valor= (valor.toLong()*60000).toString()
            return (valor.toInt()).toLong()
        }
    }
    private fun checa_pausa(pomodoro: PomodoroManager){
        if (PomodoroManager.pausado == false){
            pomodoro!!.start()
            PomodoroManager.pausado =true
            Log.i("aqui", "onCreateView: 1")

        }else{
            pomodoro!!.pausar()

            PomodoroManager.pausado =false

            Log.i("aqui", "onCreateView: 2")

        }
    }
     fun atualiza_texto(){
        val textView = view.findViewById<TextView>(R.id.textpomo)
        PomodoroManager.txt =textView
    }
    private fun clique(){
        val _tempo_pomo = view.findViewById<TextView>(R.id.tempo_pomo).text
        val _tempo_pc = view.findViewById<TextView>(R.id.tempo_pl).text
        val _tempo_pl = view.findViewById<TextView>(R.id.tempo_pc).text
        val _ticks = view.findViewById<TextView>(R.id.ticks).text
        var tempo_pomo = checa_valor(_tempo_pomo,8*60000)
        var tempo_pc =  checa_valor(_tempo_pc,5*60000)
        var tempo_pl = checa_valor(_tempo_pl,10*60000)
        var ticks = checa_valor(_ticks,5).toInt()

        PomodoroManager.pomodoro = PomodoroManager(tempo_pomo, tempo_pl,tempo_pc,ticks,c)
        checa_pausa(PomodoroManager.pomodoro!!)
    }
    init {

        atualiza_texto()
        val pomodoro_button = view.findViewById<ConstraintLayout>(R.id.startpomo)
        pomodoro_button.setOnClickListener{
            clique()
        }
        val  allowoutscreen = view.findViewById<ImageButton>(R.id.allowoutscreen)
        allowoutscreen.setOnClickListener {
            PomodoroService.switchOpenClose()
        }
        val  deletePomo = view.findViewById<ImageButton>(R.id.deletebtn)
        deletePomo.setOnClickListener {
            PomodoroManager.pomodoro?.deletar()
        }



    }

}