package com.example.PenKnife.Telas.TelaLembretes


import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

import com.example.PenKnife.R
import com.example.PenKnife.dialogs.ScreenManager
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator


class FirstFragment : Fragment() {
   

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_first, container, false)


        val remedios_Semana_lista = view.findViewById<ViewPager2>(R.id.carousel_recycler_view)
        var meus_medicamentos_lista= view.findViewById<RecyclerView>(R.id.lista_reciclavel_meus_medicamentos)

        ScreenManager.meus_medicamentos_lista=meus_medicamentos_lista
        ScreenManager.dotsIndicator=view.findViewById<WormDotsIndicator>(R.id.dotsIndicator)
        ScreenManager.lembretes_semana_lista=remedios_Semana_lista
        ScreenManager.atualiza_lista(requireContext())

        ScreenManager.firstFragment_fragmentmanager=parentFragmentManager
       // ScreenManager.cria_medicação_dialog(requireContext())

        //   Controle_de_medicações().adiciona_medicação(Medicamento("teste",8.0,30, Calendar.getInstance()),requireContext())
        return view
    }
}

class CustomItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)

        if (position == 0) { // se for o primeiro item
            outRect.left = margin

        } else if (position == parent.adapter?.itemCount?.minus(1)) { // se for o último item
            outRect.right = margin
        }
    }
}