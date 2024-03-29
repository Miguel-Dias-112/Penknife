package com.example.PenKnife.Telas.TelaFunções.Views.Blocker

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import android.widget.Button
import com.example.PenKnife.BlockerActivity
import com.example.PenKnife.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BlockerService:Service(){
    private lateinit var windowManager: WindowManager
    private lateinit var floatingButton: Button
    private lateinit var layoutParams: WindowManager.LayoutParams
    val bloqueador_coroutine = CoroutineScope(Dispatchers.Main)



    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate() {
        super.onCreate()
        bloqueador_coroutine.launch {

            fun pegarAppFoco(context: Context): Boolean? {
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
            fun bloquearTela(){
             //   floatingButton.visibility=View.VISIBLE
               // windowManager.updateViewLayout(floatingButton, layoutParams)
                var popupWindow =  Intent(applicationContext, BlockerActivity::class.java)
                popupWindow.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                applicationContext.startActivity(popupWindow)


            }
            fun desbloquearTela(){

                floatingButton.visibility=View.INVISIBLE
                windowManager.updateViewLayout(floatingButton, layoutParams)

            }
            fun controlarTela(){
                if (pegarAppFoco(applicationContext)==true ){
                    desbloquearTela()
                }else{
                    bloquearTela()
                }
            }

            while (true) {
                if (block ==true ){
                    controlarTela()
                }else{
                    desbloquearTela()
                }
                delay(100)
            }
        }
        fun criaBlockerLayout(){
            var returnbtn:Button
            var layoutparam2:LayoutParams
            fun criaFloatButton(){
                floatingButton = Button(this)
                floatingButton.text ="você saiu do app, clique ${30 - counter} vezes pra desativar o bloqueio"
                floatingButton.setBackgroundResource(R.drawable.border)
                floatingButton.setOnClickListener {
                    if (counter ==30){
                        block =false
                    }else{
                        counter +=1
                        if (counter >30){
                        }
                    }
                    floatingButton.text ="você saiu do app, clique ${30 - counter} vezes pra desativar o bloqueio"
                    windowManager.updateViewLayout(floatingButton, layoutParams)
                }
            }
            fun criareturnButton(){
                returnbtn = Button(this)
                returnbtn.text ="você saiu do app, clique ${30 - counter} vezes pra desativar o bloqueio"
                returnbtn.setBackgroundResource(R.drawable.border)
                returnbtn.setOnClickListener {
                    if (counter ==30){
                        block =false
                    }else{
                        counter +=1
                        if (counter >30){
                        }
                    }
                    returnbtn.text ="você saiu do app, clique ${30 - counter} vezes pra desativar o bloqueio"
                }
                layoutparam2 = WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
                )
                layoutparam2.gravity = Gravity.BOTTOM
                windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

                windowManager.addView(returnbtn, layoutparam2)

            }

            fun criaParametrosLayout(){
                layoutParams = WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    -1,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
                )
                layoutParams.gravity = Gravity.CENTER
            }
            fun adicionarTela(){
                windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
                windowManager.addView(floatingButton, layoutParams)


            }

            criaFloatButton()
            criaParametrosLayout()
            adicionarTela()
        }
       criaBlockerLayout()
    }
    companion object{
        var counter =0
        var block:Boolean = false
    }

}