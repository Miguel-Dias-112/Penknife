package com.example.farmcontrol.logica.Alarmes

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.media.MediaPlayer
import com.example.farmcontrol.AlarmeAtivado
import com.example.farmcontrol.R
import com.example.farmcontrol.logica.SegundoPlano.AlarmeOverlayService


class AlarmeBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, i: Intent) {
        fun invocapopup(){
            var popupWindow =  Intent(context, AlarmeAtivado::class.java)
            popupWindow.addFlags(FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(popupWindow)
        }
        invocapopup()
    }

}