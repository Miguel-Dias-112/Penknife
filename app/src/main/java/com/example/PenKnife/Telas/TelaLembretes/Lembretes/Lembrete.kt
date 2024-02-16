package com.example.PenKnife.Telas.TelaLembretes.Lembretes

import android.content.Context
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.util.Log
import com.example.PenKnife.Notificações.Alarmes.AlarmeDAO
import com.example.PenKnife.dialogs.ScreenManager

class Lembrete {
    open var datas: MutableList<MutableList<Int>>? = null
    open var intervalo_de_tempo_horas: Double? = null
    open var nome: String? = null
    open var horarios: MutableList<String> = mutableListOf()
    open var horario:String=""
    open var id:Int=0
    open var data_inicial:Calendar = Calendar.getInstance(TimeZone.getDefault())
    override fun toString(): String {
        if (nome==null){
            return listOf("null").toString()
        }else{
            return listOf<String>(nome!!,data_inicial.get(Calendar.MONTH).toString(),data_inicial.get(Calendar.DAY_OF_MONTH).toString())!!.toString()
        }
    }
    var dias_tomando: Int? = null
    constructor(lembrete: Lembrete){
        nome= lembrete.nome
        intervalo_de_tempo_horas= lembrete.intervalo_de_tempo_horas
        dias_tomando = lembrete.dias_tomando
        datas = lembrete.datas
        data_inicial=lembrete.data_inicial



    }
    constructor (
        _nome: String, _intervalo_de_tempo_horas: Double, _dias_tomando: Int, _data_inicial: Calendar,ctx: Context
    ) {


        nome = _nome
        intervalo_de_tempo_horas = _intervalo_de_tempo_horas
        dias_tomando = _dias_tomando
        datas = gerar_datas(_data_inicial)
        gerarNotificações(ctx,_nome)

        data_inicial= ScreenManager.calendar.clone() as Calendar



    }
    fun gerar_datas(data_inicial: Calendar): MutableList<MutableList<Int>>? {
        val calendar = data_inicial.clone() as Calendar
        val lista_datas = mutableListOf<MutableList<Int>>()
        var doses_ao_dia= 24/ intervalo_de_tempo_horas!!.toInt()
        var vezes_pra_tomar:Double = 0.0

        if (intervalo_de_tempo_horas!! <24){
            vezes_pra_tomar =  (dias_tomando!! *doses_ao_dia).toDouble()
        }else{
            var doses_ao_dia= 24/ intervalo_de_tempo_horas!!

            vezes_pra_tomar = dias_tomando!! *doses_ao_dia

        }

        var gamb =  calendar.get(Calendar.HOUR_OF_DAY)
        horarios!!.add("${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}")

        var stop =  false

        for (i in 1..vezes_pra_tomar!!.toInt()) {

            //salva na memoria a data
            lista_datas.add(
                mutableListOf<Int>(
                    calendar.get(Calendar.DAY_OF_MONTH).toInt(),
                    calendar.get(Calendar.MONTH).toInt(),
                    calendar.get(Calendar.YEAR).toInt(),
                    calendar.get(Calendar.HOUR_OF_DAY).toInt(),
                    calendar.get(Calendar.MINUTE).toInt(),

                    )
            )
            Log.d("datas",mutableListOf<Int>(
                calendar.get(Calendar.DAY_OF_MONTH).toInt(),
                calendar.get(Calendar.MONTH).toInt(),
                calendar.get(Calendar.YEAR).toInt(),
                calendar.get(Calendar.HOUR_OF_DAY).toInt(),
                calendar.get(Calendar.MINUTE).toInt(),

                ).toString())



            if (intervalo_de_tempo_horas!! <24){
                calendar.add(Calendar.HOUR_OF_DAY, intervalo_de_tempo_horas!!.toInt())
            }else{

                var dias_pulados = 1/(24/ intervalo_de_tempo_horas!!)

                val parteFracionaria = dias_pulados - dias_pulados.toInt()
                val horas = parteFracionaria * 24

                calendar.add(Calendar.DAY_OF_YEAR,dias_pulados.toInt())

                calendar.add(Calendar.HOUR_OF_DAY,horas.toInt())


            }

            if( gamb !=  calendar.get(Calendar.HOUR_OF_DAY) && stop==false){
                Log.d("datas_flags", "flag3")

                horarios!!.add("${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}")
            }else{
                stop=true
            }
        }


        Log.d("datas", lista_datas.toString())

        return lista_datas
    }
    fun gerarNotificações(ctx:Context,nomeLembrete:String){
        for (i in 0..(datas?.size!! - 1)){

            val diaMes = datas!![i][0]
            val mes= datas!![i][1]
            val ano = datas!![i][2]
            val hora= datas!![i][3]
            val minuto= datas!![i][4]

            val calendario = Calendar.getInstance()
            calendario.set(Calendar.DAY_OF_MONTH,diaMes)
            calendario.set(Calendar.MONTH,mes)
            calendario.set(Calendar.YEAR,ano)
            calendario.set(Calendar.HOUR_OF_DAY,hora)
            calendario.set(Calendar.MINUTE,minuto)

            AlarmeDAO(ctx).criarAlarmePontual(calendario,nomeLembrete)

        }
    }

}