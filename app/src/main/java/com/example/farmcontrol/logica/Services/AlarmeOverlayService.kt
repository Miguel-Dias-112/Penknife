package com.example.farmcontrol.logica.Services

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import com.example.farmcontrol.R

class AlarmeOverlayService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }
    fun getappinfocus(context: Context): Boolean? {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.runningAppProcesses
        if (appProcesses != null && appProcesses.size > 0) {
            for (appProcess in appProcesses) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true
                }
            }
        }
        return false
    }
    override fun onCreate() {
        super.onCreate()
        var floatingButton = Button(this)
        var windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var layoutParams=WindowManager.LayoutParams();

        fun criaCronometro(){
            fun criaFloatButton(){
                floatingButton?.text ="Seu alarme disparou, toque na tela para voltar para o sistema"
                floatingButton?.setBackgroundResource(R.drawable.border)
                floatingButton?.setOnClickListener {
                    windowManager.removeView(floatingButton)
                }


            }
            fun criaParametrosLayout(){
                layoutParams = WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    -1,
                    -1000,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
                )
                 layoutParams.gravity = Gravity.CENTER

            }
            fun adicionarTela(){
                windowManager.addView(
                    floatingButton,
                    layoutParams
                )

            }


            criaFloatButton()
            criaParametrosLayout()
            adicionarTela()
            PomodoroService.close()
        }
        criaCronometro()
    }
    companion object{
        var bloquear=false

    }

}