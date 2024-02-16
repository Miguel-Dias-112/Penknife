package com.example.PenKnife.dialogs

import android.icu.util.Calendar
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class Timepicker_dialog (manager: FragmentManager, texto_Editado: TextView){
    init{
        val picker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
                .setMinute(Calendar.getInstance().get(Calendar.MINUTE))
                .setTitleText("Select Appointment time")
                .build()

        picker.addOnPositiveButtonClickListener() {
            ScreenManager.calendar.set(Calendar.HOUR_OF_DAY, picker.hour)
            ScreenManager.calendar.set(Calendar.MINUTE, picker.minute)
            texto_Editado.setText("${picker.hour}:${picker.minute}h")
        }
        picker.show(manager!!, "tag")
    }
}