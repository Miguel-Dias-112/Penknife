package com.example.farmcontrol.Fragments.TodayFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farmcontrol.R
import com.example.farmcontrol.logica.Lembrete

class today_adpter(
    var context: Context,
    var data_set: MutableList<Lembrete>
) : RecyclerView.Adapter<today_adpter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(lembrete: Lembrete, context: Context) {

            var texto_medicamento = itemView.findViewById<TextView>(R.id.texto_medicamento)
            var hora = itemView.findViewById<TextView>(R.id.intervalo_medicamento)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): today_adpter.ViewHolder {

        val inflador = LayoutInflater.from(context)

        val view = inflador.inflate(R.layout.medicamento, parent, false)
        return today_adpter.ViewHolder(view)

    }

    override fun onBindViewHolder(holder: today_adpter.ViewHolder, position: Int) {
        var medicamento = data_set[position]
        holder.vincula(medicamento,context)

    }

    override fun getItemCount(): Int {

        return  data_set.size


    }
}