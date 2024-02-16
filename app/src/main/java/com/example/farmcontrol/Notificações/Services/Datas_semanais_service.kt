package com.example.farmcontrol.Notificações.Services

import android.icu.util.Calendar
import android.util.Log
import com.example.farmcontrol.Telas.TelaLembretes.Lembretes.Lembrete
import com.example.farmcontrol.Telas.TelaLembretes.Lembretes.LembretesDAO

class Datas_semanais_service {
    private fun  formata_horas(_horas:String, _minutos:String):String{
        var horas = _horas
        if (horas.length != 2){
            horas = "0${_horas}"
        }
        var minutos = _minutos
        if (minutos.length != 2){
            minutos = "0${_minutos}"
        }

        return "${horas}:${minutos}"
    }


    fun get_remedios_da_semana(): MutableList<MutableList<Lembrete>> {
        val remedios_da_semana = mutableListOf<MutableList<Lembrete>>(
            mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(),
            mutableListOf(), mutableListOf(), mutableListOf()
        )

        for (medicamento in LembretesDAO.lembretes) {
            val calendar = Calendar.getInstance()

            for (dia_da_semana in 0..6) {

                val dia_atual = calendar.get(Calendar.DAY_OF_MONTH)
                val mes_atual = calendar.get(Calendar.MONTH)
                val ano_atual = calendar.get(Calendar.YEAR)
                for (data in medicamento.datas!!) {
                    Log.i("semana_service", "remedio: ${medicamento} datas: ${medicamento.datas}")

                    val dia_medicamento = data[0]
                    val mes_medicamento = data[1]
                    val ano_medicamento = data[2]
                    val data_compativel =
                        dia_medicamento == dia_atual && mes_atual == mes_medicamento && ano_atual == ano_medicamento

                    if (data_compativel) {
                        Log.i("semana_service2", "remedio: ${medicamento} datas: ${medicamento.datas}")

                        val lembrete_definitivo = Lembrete(medicamento)

                        val horas = data[3].toString()
                        val minutos = data[4].toString()

                        lembrete_definitivo.horario = formata_horas(horas, minutos)
                        remedios_da_semana[dia_da_semana].add(lembrete_definitivo)
                    }
                }
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
        }
        Log.i("Semana_service", "get_lembretes_de_hoje: ${remedios_da_semana}")

        return remedios_da_semana
    }
    fun get_lembretes_de_hoje(): MutableList<Lembrete> {
        val remedios_hoje: MutableList<Lembrete> = mutableListOf()

        val calendar = Calendar.getInstance()
        val dia_atual = calendar.get(Calendar.DAY_OF_MONTH)
        val mes_atual = calendar.get(Calendar.MONTH)
        val ano_atual = calendar.get(Calendar.YEAR)

        for (medicamento in LembretesDAO.lembretes) {

            for (data in medicamento.datas!!) {
                Log.d("remedio",data.toString())
                val dia_medicamento = data[0]
                val mes_medicamento = data[1]
                val ano_medicamento = data[2]
                val data_compativel = dia_medicamento == dia_atual && mes_atual == mes_medicamento && ano_atual == ano_medicamento


                if (data_compativel) {

                    val lembrete_definitivo = Lembrete(medicamento)

                    var horas = data[3].toString()
                    if (horas.length != 2) {
                        horas = "0${data[3]}"
                    }
                    var minutos = data[4].toString()
                    if (minutos.length != 2) {
                        minutos = "0${data[4]}"
                    }
                    lembrete_definitivo.horario = "$horas:$minutos"
                    remedios_hoje.add(lembrete_definitivo)

                }
            }
        }

        Log.i("lembretes", "get_lembretes_de_hoje: ${remedios_hoje}")

        return remedios_hoje
    }

    fun dias_da_semana(): MutableList<String> {

        //pega o dia da semana e define ele como inicio
        var calendar = Calendar.getInstance()
        var dia_da_semana = calendar.get(Calendar.DAY_OF_WEEK)-1
        var semana = mutableListOf<String>()


        for (contador in 0..6){
            semana.add(nome_dos_dias[dia_da_semana])

            if (dia_da_semana+1>=7){
                dia_da_semana = 0
            }else{
                dia_da_semana += 1
            }
        }

        return semana
    }


    companion object{
        lateinit var dias_da_semana_str: MutableList<String>
        val nome_dos_dias = listOf("Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado")


    }
}