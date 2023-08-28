package com.example.farmcontrol.Fragments.LembretesAgendados_fragment.Remedios

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.farmcontrol.R
import com.example.farmcontrol.dialogs.ScreenManager
import com.example.farmcontrol.logica.Lembrete
import com.example.farmcontrol.logica.LembretesDao
import com.google.android.material.textview.MaterialTextView

class MedicamentosListaReciclavelAdapter(var context: Context) :
    RecyclerView.Adapter<MedicamentosListaReciclavelAdapter.ViewHolder>() {

    var listaMedicamentos = LembretesDao.lembretes!!
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(lembrete: Lembrete, context: Context) {
            val intervaloDeTempoHoras = lembrete.intervalo_de_tempo_horas!!
            val nomeMedicamento = lembrete.nome!!
            val titulo = itemView.findViewById<TextView>(R.id.minha_medicação_titulo)
            val intervalo = itemView.findViewById<TextView>(R.id.minha_medicação_intervalo)
            val medicamentoLayout: ConstraintLayout = itemView.findViewById(R.id.medicamento)

            titulo.text = nomeMedicamento
            intervalo.text =
                "de " + intervaloDeTempoHoras.toInt().toString() + " em " + intervaloDeTempoHoras.toInt().toString() + " horas"

            medicamentoLayout.setOnLongClickListener {
                LembretesDao().remove_lembrete(lembrete, context)
                true
            }
        }

        fun vinculaBotao(context: Context) {
            val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)
            val botao = itemView.findViewById<ConstraintLayout>(R.id.container)
            val minha_medicação_titulo = itemView.findViewById<MaterialTextView>(R.id.minha_medicação_titulo)

            minha_medicação_titulo.visibility = View.INVISIBLE
            botao.visibility = View.VISIBLE

            imageButton.setOnClickListener {

                ScreenManager.criaLembreteDialog(context)


            }
        }
        @SuppressLint("ResourceAsColor")
        fun vinculaPlaceHolder(context: Context) {
            val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)
            val botao = itemView.findViewById<ConstraintLayout>(R.id.container)
            val minha_medicação_titulo = itemView.findViewById<MaterialTextView>(R.id.minha_medicação_titulo)
            val minha_medicação_intervalo = itemView.findViewById<MaterialTextView>(R.id.minha_medicação_intervalo)

            minha_medicação_intervalo.setText("TUTORIAL")
            minha_medicação_titulo.visibility = View.VISIBLE
            minha_medicação_titulo.setText("Crie no botão.\nExclua segurando.\nEdite com 2 toques(não implementado).")

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.minhas_medicacoes_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(listaMedicamentos.size==0 && position==1){
            holder.vinculaPlaceHolder(context)
        }
        if (position == 0) {
            holder.vinculaBotao(context)
        }

        if(listaMedicamentos.size!=0 && position!=0){

            val medicamento = listaMedicamentos[position - 1]
            holder.vincula(medicamento, context)
        }
    }

    override fun getItemCount(): Int {

        if(listaMedicamentos.size==0){
            return 2
        }
        return listaMedicamentos.size + 1
    }
}
