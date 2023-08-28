package com.example.farmcontrol.dialogs

import android.app.FragmentManager
import android.content.Context
import android.os.Build
import android.widget.TextView
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class Datapicker_dialog(manager: androidx.fragment.app.FragmentManager, texto_Editado: TextView, context: Context){
    init{
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->

            fun Long.toLocalDateTime() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            fun Long.toUTCLocalDateTime() = LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.ofOffset("UTC", ZoneOffset.UTC))
            val dateLong = selection!!.toUTCLocalDateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            ScreenManager.calendar.timeInMillis = dateLong
            texto_Editado.text = SimpleDateFormat("dd/MM/yyyy").format(ScreenManager.calendar?.time)

        }
        datePicker.show(manager!!, "tag")
    }
}