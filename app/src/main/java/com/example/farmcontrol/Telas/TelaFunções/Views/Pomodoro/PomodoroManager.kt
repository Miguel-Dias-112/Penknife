package com.example.farmcontrol.Telas.TelaFunções.Views.Pomodoro

import android.content.Context
import android.util.Log
import android.widget.TextView
import com.example.farmcontrol.Notificações.Alarmes.AlarmeDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PomodoroManager(
    var tempo_pomodoro:Long,
    var tempo_de_pausaLonga:Long,
    var tempo_de_pausaCurta:Long,
    var ticks_pausaLonga:Int,
    var c:Context,
    )

{


    fun deletar(){
        cronometro.cancel()
        cronometro =CoroutineScope(Dispatchers.Main)
        txt?.setText("iniciar")
        PomodoroService.mudartexto("00:00")
        PomodoroService.close()
        numero_ticks =0
        tempo_decorrido =0
        pausa =false
        pausado =false
    }
    fun pausar(){
        cronometro.cancel()
        cronometro =CoroutineScope(Dispatchers.Main)
        txt?.setText("pausado")
        PomodoroService.mudartexto("pausado")

        Log.i("aqui", "pausar: 1")
    }

    suspend fun pausas(){
        var alarmeFactory = AlarmeDAO(c)
        if(numero_ticks ==ticks_pausaLonga) {
            txt!!.setText("pausa longa "+(tempo_de_pausaLonga/1000 - tempo_decorrido /1000)+"")
            PomodoroService.mudartexto("\"pausa longa \"+(tempo_de_pausaLonga/1000 - tempo_decorrido /1000)+\"\"")

            if (tempo_decorrido ==tempo_de_pausaLonga){
                numero_ticks =0
                tempo_decorrido =0
                alarmeFactory.tocarSom()
                delay(100)
                pausar()
            }
        }
        else{
            txt!!.setText("pausa curta "+(tempo_de_pausaCurta/1000 - tempo_decorrido /1000)+"")
            PomodoroService.mudartexto("pausa curta \"+(tempo_de_pausaCurta/1000 - tempo_decorrido /1000)+\"")
            if (tempo_decorrido ==tempo_de_pausaCurta){

                tempo_decorrido =0
                alarmeFactory.tocarSom()
                pausa =false

                delay(400)
            }
        }
    }
    fun start(){
        cronometro.launch {
            while (true) {
                delay(1000)
                tempo_decorrido +=1000
                //detecta que o tempo do pomo acabou
                if (tempo_decorrido ==tempo_pomodoro && pausa ==false){
                    tempo_decorrido =0
                    pausa =true
                }
                //detecta se está pausado
                if (pausa){
                    var alarmeFactory = AlarmeDAO(c)
                    //notifica que está pausado
                    if (tempo_decorrido ==0L){
                        alarmeFactory.tocarSom()
                        delay(100)
                        numero_ticks +=1
                    }
                    //detecta se é uma pausa longa ou curta
                    pausas()
                }else{
                    val texto= "pomodoro: "+(tempo_decorrido /1000)+"s | "+tempo_pomodoro/1000+"s\nticks: $numero_ticks"
                    val texto2= ""+(tempo_decorrido /1000)+" | "+tempo_pomodoro/1000+"\nticks: $numero_ticks"

                    txt?.setText(texto )
                    PomodoroService.mudartexto(texto2)
                }
            }
        }
    }
    companion object{
        var cronometro = CoroutineScope(Dispatchers.Main)
        var numero_ticks:Int = 0
        var tempo_decorrido:Long=0
        var pausa = false
        var txt:TextView?=null
        var pomodoro: PomodoroManager?=null
        var pausado = false

    }
}