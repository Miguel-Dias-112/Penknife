package com.example.farmcontrol.Fragments.TodayFragment.Alarm

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
import com.example.farmcontrol.logica.Blocker.CronometroService

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
                    if(getappinfocus(applicationContext) == false){
                        windowManager.updateViewLayout(
                            floatingButton,
                            layoutParams
                        )

                    }

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

                //  layoutParams.gravity = Gravity.TOP or Gravity.START
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
            CronometroService.close()
        }
        criaCronometro()
    }
    companion object{
        var bloquear=false

    }

}