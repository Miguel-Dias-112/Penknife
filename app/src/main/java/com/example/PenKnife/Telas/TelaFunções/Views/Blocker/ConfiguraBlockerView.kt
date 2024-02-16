package com.example.PenKnife.Telas.TelaFunções.Views.Blocker

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Switch
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.PenKnife.R

class ConfiguraBlockerView(var view:View, var context:Context) {
    val SwitchBlocker:Switch =view.findViewById<Switch>(R.id.switch3)
    init {
        SwitchBlocker.setOnCheckedChangeListener { _, isChecked ->
            if (SwitchBlocker.isChecked) {
                BlockerService.block=true
            } else {
                BlockerService.block=false
            }
        }

        val blocker_ctn = view.findViewById<ConstraintLayout>(R.id.blocker_ctn)
        blocker_ctn.setOnClickListener {
            if (BlockerService.block==false){
                BlockerService.block=true
                SwitchBlocker.isChecked=true
            }else{
                BlockerService.block=false
                SwitchBlocker.isChecked=false
            }

            context.startService(Intent(context, BlockerService::class.java))
        }
    }

    fun AtualizaBlocker(){
        try{
            SwitchBlocker?.isChecked= BlockerService.block
            BlockerService.counter=0

        }catch (e:Exception){
        }
    }
}