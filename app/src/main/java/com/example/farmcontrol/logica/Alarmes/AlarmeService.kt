package com.example.farmcontrol.logica.Alarmes

import android.content.BroadcastReceiver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import com.example.farmcontrol.R


class AlarmeService : BroadcastReceiver() {

    override fun onReceive(c: Context, i: Intent) {
        val action = i?.action
        val mp: MediaPlayer = MediaPlayer.create(c, R.raw.trim)
        fun tocarSom(){
            mp.setOnCompletionListener { mp -> mp.release() }
            mp.start()
        }
        fun pararSom(){
            mp.stop();
        }

        if (action!="stop"){
            NotificationFactory(c).notifica()
            tocarSom()
        }else{
            pararSom()
        }
    }
}