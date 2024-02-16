package com.example.PenKnife.Notificações.Alarmes

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.media.MediaPlayer
import com.example.PenKnife.R

class AlarmeDAO(var ctx: Context) {

    val intent = Intent(ctx!!, AlarmeBroadcast::class.java)
    val alarmManager = ctx!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @SuppressLint("ScheduleExactAlarm")
    fun criarAlarmePontual(calendar: Calendar,nome:String){
        var calendar=calendar;
        val horaDoAlarme = calendar.timeInMillis
        val tipoDoAlarme= AlarmManager.RTC_WAKEUP

        intent.setAction("EXECUTAR_ALARME")
        intent.putExtra("nome_alarme",nome)
        val intençãoDoAlarme = PendingIntent.getBroadcast(ctx!!, 1, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setExact(tipoDoAlarme, horaDoAlarme, intençãoDoAlarme)



    }
    fun tocarSom(){
        val mp: MediaPlayer = MediaPlayer.create(ctx, R.raw.trim)
        mp.setOnCompletionListener { mp -> mp.release() }
        mp.start()
    }
    fun alarmes_lista(codigo_request:Int){
        intent.setAction("EXECUTAR_ALARME")
        val alarme = PendingIntent.getBroadcast(
            ctx!!,
            codigo_request,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val alarmTime = System.currentTimeMillis() + 60000
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, alarme);
    }



}
