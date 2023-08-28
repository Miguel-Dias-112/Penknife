package com.example.farmcontrol.Fragments.TodayFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.farmcontrol.R
import com.example.farmcontrol.dialogs.ScreenManager
import com.example.farmcontrol.logica.Blocker.BlockerService
import com.example.farmcontrol.logica.Pomodoro.PomodoButton

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    fun atualiza_lista(c: Context){
        ScreenManager.atualiza_lista(c)
    }
    var x: Switch? =null
    @SuppressLint("MissingInflatedId")
    override fun onStart() {
        super.onStart()
        try{
            x?.isChecked=BlockerService.block
            BlockerService.counter=0

            if (BlockerService.block==false){
            }
        }catch (e:Exception){

        }

    }
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
        val switch3 = view.findViewById<Switch>(R.id.switch3)
        switch3.setOnCheckedChangeListener { _, isChecked ->
            // Lógica para lidar com a alteração do switch button
            if (isChecked) {
                BlockerService.block=true

            } else {
                BlockerService.block=false
            }
        }
        val blocker_ctn = view.findViewById<ConstraintLayout>(R.id.blocker_ctn)
        blocker_ctn.setOnClickListener {
            Log.i("foda", "onCreateView:existo ")
            if (BlockerService.block==false){
                BlockerService.block=true
                switch3.isChecked=true
            }else{
                BlockerService.block=false
                switch3.isChecked=false


            }
            requireContext().startService(Intent(context, BlockerService::class.java))

        }

        ScreenManager.lembretes_dia_lista=view.findViewById<RecyclerView>(R.id.recyclerView)
        PomodoButton(view,requireContext())
        atualiza_lista(requireContext())
        BlockerService.counter=0
        x=switch3
        return view
    }

}