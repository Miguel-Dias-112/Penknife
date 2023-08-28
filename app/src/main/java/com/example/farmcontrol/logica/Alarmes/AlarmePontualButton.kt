package com.example.farmcontrol.logica.Alarmes

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.farmcontrol.R

class AlarmePontualButton(view: View,c:Context) {

    init {
        val alarme_pontual= view.findViewById<ConstraintLayout>(R.id.alarme_pontual)
        alarme_pontual.setOnClickListener {
          //  CriaLembretePontual_dialog(c,parentFragmentManager)
        }

    }
}