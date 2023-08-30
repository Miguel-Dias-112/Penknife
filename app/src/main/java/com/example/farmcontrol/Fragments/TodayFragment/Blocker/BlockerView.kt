package com.example.farmcontrol.Fragments.TodayFragment.Blocker

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Switch
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.farmcontrol.R

class BlockerView(var view:View, var context:Context) {
    val switch3:Switch =view.findViewById<Switch>(R.id.switch3)
    init {
        switch3.setOnCheckedChangeListener { _, isChecked ->
            // Lógica para lidar com a alteração do switch button
            if (switch3.isChecked) {
                BlockerService.block=true

            } else {
                BlockerService.block=false
            }
        }
        val blocker_ctn = view.findViewById<ConstraintLayout>(R.id.blocker_ctn)
        blocker_ctn.setOnClickListener {
            if (BlockerService.block==false){
                BlockerService.block=true
                switch3.isChecked=true
            }else{
                BlockerService.block=false
                switch3.isChecked=false


            }
            context.startService(Intent(context, BlockerService::class.java))

        }

    }

    fun façalogica(){
        try{
            switch3?.isChecked= BlockerService.block
            BlockerService.counter=0
            Log.i("TAG", "façalogica: 2")

        }catch (e:Exception){
            Log.i("TAG", "façalogica: ")
        }
    }
}