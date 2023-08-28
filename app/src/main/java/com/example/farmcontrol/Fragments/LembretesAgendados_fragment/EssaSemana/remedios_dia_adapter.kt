package com.example.farmcontrol.Fragments.LembretesAgendados_fragment.EssaSemana

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.farmcontrol.R
import com.example.farmcontrol.logica.Lembrete

class remedios_dia_adapter(
    var context: Context,
    var medicamentos_semana: MutableList<Lembrete>
) : RecyclerView.Adapter<remedios_dia_adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflador = LayoutInflater.from(context)
        val view = inflador.inflate(R.layout.medicamento, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, dia_da_semana: Int) {
        val medicamento = medicamentos_semana[dia_da_semana]
        holder.vincula(medicamento, context)
    }

    override fun getItemCount(): Int {
        return medicamentos_semana.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(lembrete: Lembrete, context: Context) {
            var titulo = itemView.findViewById<TextView>(R.id.texto_medicamento)
            var intervalo = itemView.findViewById<TextView>(R.id.intervalo_medicamento)
            titulo.setText(lembrete.nome)
            intervalo.setText(lembrete.horario)

        }
    }

}