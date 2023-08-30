package com.example.farmcontrol.logica.Alarmes

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.PopupWindow
import com.example.farmcontrol.Fragments.TodayFragment.Alarm.AlarmeOverlayService
import com.example.farmcontrol.R


class AlarmeBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, i: Intent) {
        var popupWindow =  Intent(context, AlarmeOverlayService::class.java)
        AlarmeBroadcast.popupWindow=popupWindow
        val action = i?.action
        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.trim)
        fun tocarSom(){
            mp.setOnCompletionListener { mp -> mp.release() }
            mp.start()
        }
        fun pararSom(){
            mp.stop();
        }
        fun invocapopup(){
            context.startService(popupWindow)
        }

        if (action!="stop"){
            NotificationFactory(context).notifica()
            invocapopup()
           // tocarSom()
        }else{
            pararSom()

        }
    }
    companion object{
        var popupWindow:Intent = Intent()
    }
}