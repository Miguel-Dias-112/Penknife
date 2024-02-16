package com.example.PenKnife.Notificações.Alarmes

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.example.PenKnife.AlarmeAtivadoActivity


class AlarmeBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, i: Intent) {
        val action = i?.action


        fun invocapopup(){
            val nome = i.getStringExtra("nome_alarme")
            var popupWindow =  Intent(context, AlarmeAtivadoActivity::class.java).putExtra("nome_alarme",nome)
            popupWindow.addFlags(FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(popupWindow)
        }
        if (action=="EXECUTAR_ALARME"){
            invocapopup()
        }
    }
    companion object{
        var popupWindow:Intent = Intent()
    }
}