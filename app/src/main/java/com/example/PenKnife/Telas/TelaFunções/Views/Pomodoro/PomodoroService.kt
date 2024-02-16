package com.example.PenKnife.Telas.TelaFunções.Views.Pomodoro

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.example.PenKnife.MainActivity
import com.example.PenKnife.R

class PomodoroService:Service(){
    private var initialX = 0f
    private var initialY = 0f
    private var initialTouchX: Float = 0.toFloat()
    private var initialTouchY: Float = 0.toFloat()
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate() {
        super.onCreate()
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

        fun criaCronometro(){
            fun criaFloatButton(){
                floatingButton = Button(this)
                floatingButton.text ="00:00"
                floatingButton.setBackgroundResource(R.drawable.border)
                floatingButton.setOnClickListener {
                    if(getappinfocus(applicationContext) == false){
                        windowManager.updateViewLayout(floatingButton, layoutParams)
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                }
                floatingButton.setOnTouchListener(object : View.OnTouchListener {
                    override fun onTouch(v: View, event: MotionEvent): Boolean {
                        when (event.action) {
                            MotionEvent.ACTION_DOWN -> {
                                initialX = layoutParams.x.toFloat()
                                initialY = layoutParams.y.toFloat()
                                initialTouchX = event.rawX
                                initialTouchY = event.rawY
                            }

                            MotionEvent.ACTION_UP ->{

                            }
                            MotionEvent.ACTION_MOVE -> {
                                val deltaX = event.rawX - initialTouchX
                                val deltaY = event.rawY - initialTouchY
                                layoutParams.x = (initialX + deltaX).toInt()
                                layoutParams.y = (initialY + deltaY).toInt()
                                windowManager.updateViewLayout(floatingButton, layoutParams)
                            }
                        }
                        return false
                    }
                })

            }
            fun criaParametrosLayout(){
                layoutParams = WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    -1,
                    -1000,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
                )
              //  layoutParams.gravity = Gravity.TOP or Gravity.START
            }
            fun adicionarTela(){
                windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
                windowManager.addView(floatingButton, layoutParams)

            }


            criaFloatButton()
            criaParametrosLayout()
            adicionarTela()
            close()
            Log.i("cronometro", "criaCronometro: ")
        }
       criaCronometro()
    }
    companion object{

        var isclosed:Boolean=false
        private lateinit var floatingButton: Button
        private lateinit var windowManager: WindowManager
        private lateinit var layoutParams: WindowManager.LayoutParams
        fun mudartexto(texto:String){
            floatingButton.text ="${texto}"
            windowManager.updateViewLayout(floatingButton, layoutParams)

        }
        fun close(){
            floatingButton.visibility=View.INVISIBLE
            windowManager.updateViewLayout(floatingButton, layoutParams)
            isclosed = false

        }
        fun open(){
            floatingButton.visibility=View.VISIBLE
            windowManager.updateViewLayout(floatingButton, layoutParams)
            isclosed = true

        }
        fun switchOpenClose(){
            if(isclosed){
                close()

            }else{
                open()
            }
        }
    }

}