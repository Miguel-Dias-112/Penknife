package com.example.farmcontrol.Fragments.HomeFrag

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.farmcontrol.R
import com.example.farmcontrol.dialogs.ScreenManager
import com.example.farmcontrol.logica.Services.BlockerService
import com.example.farmcontrol.Fragments.HomeFrag.Views.Blocker.BlockerView
import com.example.farmcontrol.Fragments.HomeFrag.Views.Pomodoro.PomodoroView

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    var blockerView: BlockerView? =null
    fun atualiza_lista(c: Context){
        ScreenManager.atualiza_lista(c)
    }
    @SuppressLint("MissingInflatedId")
    override fun onStart() {
        super.onStart()
        Log.i("TAG", "onStart: ")
        blockerView?.façalogica()


    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_second, container, false)


        val alarme_pontual= view.findViewById<ConstraintLayout>(R.id.alarme_pontual)
        alarme_pontual.setOnClickListener {
            ScreenManager.secondFragmentManager=parentFragmentManager
            ScreenManager.criaLembrePontualDialog(requireContext())
        }



        try {
            blockerView= BlockerView(view,requireContext())
            ScreenManager.lembretes_dia_lista=view.findViewById<RecyclerView>(R.id.recyclerView)
            PomodoroView(view,requireContext())
            atualiza_lista(requireContext())

        }catch (e:Exception){
        }
        BlockerService.counter=0
        return view
    }

}