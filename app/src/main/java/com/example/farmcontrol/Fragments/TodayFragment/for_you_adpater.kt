package com.example.farmcontrol.Fragments.LembretesAgendados_fragment.EssaSemana

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.farmcontrol.R

class for_you_adpater(
    var context: Context,
    var data_set: MutableList<String>
) : RecyclerView.Adapter<for_you_adpater.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflador = LayoutInflater.from(context)
        val view = inflador.inflate(R.layout.carrousel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, dia_da_semana: Int) {
        val medicamento = data_set[dia_da_semana]
        holder.vincula("medicamento", context)
    }


    override fun getItemCount(): Int {
        return data_set.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(texto: String, context: Context) {

            Log.d("2fragmente", "carrousel")
        }
    }

}