package com.example.farmcontrol.Telas.TelaLembretes.Lembretes

import android.content.Context
import android.icu.util.Calendar
import android.util.Log
import androidx.room.Room
import com.example.farmcontrol.dialogs.ScreenManager
import com.example.farmcontrol.logica.Database.LembretesAgendados.DatabaseApp
import com.example.farmcontrol.logica.Database.LembretesAgendados.Models.LembreteModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LembretesDAO {

    fun lembrete_converter(lembrete: Lembrete): LembreteModel {
        Log.i("calendar", "lembrete_converter: ${lembrete}")
        return LembreteModel(
            Calendar.getInstance().timeInMillis.toInt(),
            lembrete.dias_tomando!!,
            lembrete.intervalo_de_tempo_horas!!.toFloat(),
            lembrete.nome!!,
            lembrete.data_inicial.get(Calendar.YEAR),
            lembrete.data_inicial.get(Calendar.MONTH),
            lembrete.data_inicial.get(Calendar.DAY_OF_MONTH),
            lembrete.data_inicial.get(Calendar.HOUR_OF_DAY),
            lembrete.data_inicial.get(Calendar.MINUTE),

            )
    }
    fun lembrete_converter(lembrete: LembreteModel): Lembrete {

        var data= Calendar.getInstance()
        data.set(Calendar.YEAR,lembrete.ano)
        data.set(Calendar.MONTH,lembrete.mes)
        data.set(Calendar.DAY_OF_MONTH,lembrete.dia)
        data.set(Calendar.HOUR_OF_DAY,lembrete.hora)
        data.set(Calendar.MINUTE,lembrete.minuto)
        Log.i("calendar", "criação converter: " +
                " mes ${data.get(Calendar.MONTH)}  " +
                "dia ${data.get(Calendar.DAY_OF_MONTH)} " +
                "nome ${lembrete.titulo}")

        var lembrete_novo = Lembrete(
             lembrete.titulo,
             lembrete.intervalo_de_tempo_horas.toDouble(),
             lembrete._dias_tomando,
             data)
        lembrete_novo.id=lembrete.id
        return lembrete_novo
    }
    fun load_lembretes(c:Context){
        CoroutineScope(Dispatchers.Default).launch{
            val db = Room.databaseBuilder(
                c,
                DatabaseApp::class.java, "lembretes"
            ).build()

            val userDao = db.lembreteDao()
            val lembretes: List<LembreteModel> = userDao.getAll()
            for(lembretemodel in lembretes){
                val conversão = lembrete_converter(lembretemodel)
                carrega_lembrete(conversão,c)
            }

        }

    }
    fun carrega_lembrete(lembrete: Lembrete, context:Context){
        lembretes!!.add(lembrete)
        ScreenManager.atualiza_lista(context)

    }
    fun adiciona_lembrete(lembrete: Lembrete, context:Context){
        lembretes!!.add(lembrete)
        ScreenManager.atualiza_lista(context)
        Log.i("lembrete", "adiciona_lembrete: ${lembrete} ")
        CoroutineScope(Dispatchers.Default).launch {
            val db = Room.databaseBuilder(
                context,
                DatabaseApp::class.java, "lembretes"
            ).build()
            val userDao = db.lembreteDao()
            val lembretemodel=lembrete_converter(lembrete)
            userDao.insertAll(lembretemodel)
        }


    }
    fun remove_lembrete(lembrete: Lembrete, context: Context){
        val index = lembretes!!.indexOf(lembrete)
        lembretes!!.removeAt(index)
        ScreenManager.atualiza_lista(context)
        CoroutineScope(Dispatchers.Default).launch {
            val db = Room.databaseBuilder(
                context,
                DatabaseApp::class.java, "lembretes"
            ).build()
            val userDao = db.lembreteDao()
            val lembretemodel=lembrete_converter(lembrete)
            userDao.deleteByUserId(lembrete.id)
        }
    }
    fun edita_lembrete(lembrete_antigo: Lembrete, lembrete_novo: Lembrete){
        val index = lembretes!!.indexOf(lembrete_antigo)
        lembretes!![index]=lembrete_novo
    }

    companion object{
        var lembretes: MutableList<Lembrete> = mutableListOf()

    }
}