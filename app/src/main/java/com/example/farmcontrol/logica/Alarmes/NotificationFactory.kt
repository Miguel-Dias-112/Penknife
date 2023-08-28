package com.example.farmcontrol.logica.Alarmes

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.farmcontrol.R


class NotificationFactory {
    var ctx:Context?=null
    constructor(_ctx:Context){
        ctx=_ctx
        fun createNotificationChannel() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name ="teste"
                val descriptionText = "teste"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel("TESTE", name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager = ctx!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationManager = ctx!!.getSystemService(NotificationManager::class.java)
                val channel = notificationManager.getNotificationChannel("TESTE")

                // Verificar se o canal de notificação precisa de permissão do usuário
                if (channel.importance == NotificationManager.IMPORTANCE_NONE) {

                    val intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
                    intent.putExtra(Settings.EXTRA_APP_PACKAGE, ctx!!.packageName)
                    intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.id)
                    ctx!!.startActivity(intent)
                }
            }
        }

        createNotificationChannel()
    }

    fun notifica(){

        val stop_notification = Intent(ctx!!, AlarmeService::class.java).apply {
            action = "stop"
        }
        val stopIntent: PendingIntent =
            PendingIntent.getBroadcast(ctx!!, 0, stop_notification, PendingIntent.FLAG_IMMUTABLE)



        var builder = NotificationCompat.Builder(ctx!!, "TESTE")
            .setSmallIcon(com.google.android.material.R.drawable.abc_ic_menu_overflow_material)
            .setContentTitle("Você tem uma tarefa pendente")
            .setContentText("você tem que fazer isso aqui")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .addAction(
                R.drawable.ic_launcher_foreground, "parar",
                stopIntent)



        if (ActivityCompat.checkSelfPermission(
                ctx!!,
                Manifest.permission.SET_ALARM
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Toast.makeText(ctx!!,"você é uma gostosa",Toast.LENGTH_SHORT).show()
            NotificationManagerCompat.from(ctx!!).notify(1000,builder.build())


            return
        }


    }


}