package com.example.farmcontrol.Fragments.LembretesAgendados_fragment.EssaSemana

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmcontrol.R
import com.example.farmcontrol.logica.Datas_semanais_service
import com.example.farmcontrol.logica.Lembrete

class remedios_semana_adpater(var context: Context) : RecyclerView.Adapter<remedios_semana_adpater.ViewHolder>() {

    var medicamentos = Datas_semanais_service().dias_da_semana()!!

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(medicamento: String, context: Context, medicametos_da_semana:MutableList<Lembrete>) {
            var titulo = itemView.findViewById<TextView>(R.id.minha_medicação_titulo)
            titulo.setText(medicamento)

            var wmedicamentos_recyclerview= itemView.findViewById<RecyclerView>(R.id.wmedicamentos_recyclerview)
            val remediosDaSemanaAdpater = remedios_dia_adapter(context,medicametos_da_semana)

            wmedicamentos_recyclerview.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
            wmedicamentos_recyclerview.adapter=remediosDaSemanaAdpater

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflador = LayoutInflater.from(context)
        val view = inflador.inflate(R.layout.dia_semana, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val medicamento = medicamentos[position]
        var medicametos_da_semana = Datas_semanais_service().get_remedios_da_semana()

        holder.vincula(medicamento, context, medicametos_da_semana[position])


    }





    override fun getItemCount() = 7
}