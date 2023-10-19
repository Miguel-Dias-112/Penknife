package com.example.farmcontrol.dialogs

import android.content.Context
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.farmcontrol.Fragments.HomeFrag.remedios_dia_adapter
import com.example.farmcontrol.Fragments.LembretesAgendadosFrag.EssaSemana.remedios_semana_adpater
import com.example.farmcontrol.Fragments.LembretesAgendadosFrag.Remedios.MedicamentosListaReciclavelAdapter
import com.example.farmcontrol.logica.Services.Datas_semanais_service
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class ScreenManager() {

    companion object{
        var meus_medicamentos_lista: RecyclerView? = null
        var lembretes_semana_lista: ViewPager2? = null
        var lembretes_dia_lista: RecyclerView? = null
        var dotsIndicator:WormDotsIndicator? =null
        var firstFragment_fragmentmanager: FragmentManager? = null
        var secondFragmentManager: FragmentManager? = null
        var calendar:Calendar = Calendar.getInstance(TimeZone.getDefault())


        fun timepicker( manager:FragmentManager,texto_Editado:TextView ){
            Timepicker_dialog(manager,texto_Editado)
        }
        fun datapicker( manager:FragmentManager, texto_Editado:TextView,context:Context){
            Datapicker_dialog(manager,texto_Editado,context)
        }
        fun criaLembreteDialog(context: Context){
            CriaLembrete_dialog(context)
        }
        fun criaLembrePontualDialog(context: Context){
            CriaLembretePontual_dialog(context, secondFragmentManager!!)
        }

        fun atualiza_lista(  context: Context){
            try {


            val adapter_remedios_semana = remedios_semana_adpater(context)
           // lembretes_semana_lista?.setLayoutManager(LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false))
            lembretes_semana_lista?.adapter = adapter_remedios_semana

            Log.i("nullcatce", "atualiza_lista: $dotsIndicator $lembretes_semana_lista")
            try {
                 dotsIndicator!!.attachTo(lembretes_semana_lista!!)

            }catch (e:Exception){

            }

            val lista_reciclavel_meus_medicamentos_adpater = MedicamentosListaReciclavelAdapter(context)
            meus_medicamentos_lista?.setLayoutManager(GridLayoutManager(context,2))
            meus_medicamentos_lista?.adapter=lista_reciclavel_meus_medicamentos_adpater

            val lista_reciclavel_meus_lembretes_diarios =  remedios_dia_adapter(context, Datas_semanais_service().get_lembretes_de_hoje())
            lembretes_dia_lista?.adapter = lista_reciclavel_meus_lembretes_diarios
            lembretes_dia_lista?.setLayoutManager(LinearLayoutManager(context))}
            catch (e:Exception){

            }
        }


    }

}