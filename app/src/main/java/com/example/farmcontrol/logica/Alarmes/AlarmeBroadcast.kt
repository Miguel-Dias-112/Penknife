package com.example.farmcontrol.logica.Alarmes

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import com.example.farmcontrol.AlarmeAtivadoAct


class AlarmeBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, i: Intent) {
        val action = i?.action


        fun invocapopup(){
            var popupWindow =  Intent(context, AlarmeAtivadoAct::class.java)
            popupWindow.addFlags(FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(popupWindow)
        }
        Log.i("alarme pontual", action!!)

        if (action=="EXECUTAR_ALARME"){
            invocapopup()
            Log.i("alarme pontual", "onReceive: Trimmmmmmmmmmmm")
        }
    }
    companion object{
        var popupWindow:Intent = Intent()
    }
}