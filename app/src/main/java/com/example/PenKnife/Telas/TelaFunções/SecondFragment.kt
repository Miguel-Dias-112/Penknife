package com.example.PenKnife.Telas.TelaFunções

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.PenKnife.R
import com.example.PenKnife.dialogs.ScreenManager
import com.example.PenKnife.Telas.TelaFunções.Views.Blocker.BlockerService
import com.example.PenKnife.Telas.TelaFunções.Views.Blocker.ConfiguraBlockerView
import com.example.PenKnife.Telas.TelaFunções.Views.Pomodoro.ConfiguraPomodoroView

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    var blockerView: ConfiguraBlockerView? =null
    fun atualiza_lista(c: Context){
        ScreenManager.atualiza_lista(c)
    }
    @SuppressLint("MissingInflatedId")
    override fun onStart() {
        super.onStart()
        blockerView?.AtualizaBlocker()


    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_second, container, false)


        val alarme_pontual= view.findViewById<ConstraintLayout>(R.id.alarme_pontual)
        alarme_pontual.setOnClickListener {
            ScreenManager.secondFragmentManager=parentFragmentManager
            ScreenManager.criaLembrePontualDialog(requireContext())
        }



        try {
            blockerView= ConfiguraBlockerView(view,requireContext())
            ConfiguraPomodoroView(view,requireContext())

            ScreenManager.lembretes_dia_lista=view.findViewById<RecyclerView>(R.id.recyclerView)
            atualiza_lista(requireContext())

        }catch (e:Exception){
        }
        BlockerService.counter=0
        return view
    }

}