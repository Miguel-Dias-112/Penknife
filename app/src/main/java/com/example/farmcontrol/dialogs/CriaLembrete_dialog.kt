package com.example.farmcontrol.dialogs

import android.app.AlertDialog
import android.content.Context
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.example.farmcontrol.R
import com.example.farmcontrol.dialogs.ScreenManager.Companion.calendar
import com.example.farmcontrol.dialogs.ScreenManager.Companion.datapicker
import com.example.farmcontrol.dialogs.ScreenManager.Companion.firstFragment_fragmentmanager
import com.example.farmcontrol.dialogs.ScreenManager.Companion.timepicker
import com.example.farmcontrol.logica.Lembrete
import com.example.farmcontrol.logica.LembretesDao
import com.google.android.material.textfield.TextInputEditText

class CriaLembrete_dialog// Lógica a ser executada quando o usuário para de interagir com a SeekBar// Lógica a ser executada quando o usuário começa a interagir com a SeekBar// Lógica a ser executada quando o progresso da SeekBar é alterad//val botão_cancelar = view.findViewById<Button>(R.id.cancelar_projeto)
    (context: Context) {

    init {
            calendar= Calendar.getInstance(TimeZone.getDefault())
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.add_medicacao_dialog, null)
            val dialoa = AlertDialog.Builder(context).create()

            val textinput = view.findViewById<TextInputEditText>(R.id.nome_novomedicamento)
            val botão_criar = view.findViewById<Button>(R.id.confirmar_novomedicamento)
            val botão_cancelar = view.findViewById<Button>(R.id.button4)

            //val botão_cancelar = view.findViewById<Button>(R.id.cancelar_projeto)
            val data_texto = view.findViewById<TextView>(R.id.textView5)
            val hora_texto = view.findViewById<TextView>(R.id.textView6)
            val dias_texto = view.findViewById<TextView>(R.id.textView11)

            val interval = view.findViewById<TextInputEditText>(R.id.textView7)
            val dias_tomando_view = view.findViewById<SeekBar>(R.id.seekBar3)
            var dias_tomando = 0
            var ultimo_dias_tomando = 0

            val checkBox=view.findViewById<CheckBox>(R.id.checkBox)

            val datapicker_btn = view.findViewById<ImageButton>(R.id.getdata_btn_novomedicamento)
            val timepicker_btn = view.findViewById<ImageButton>(R.id.get_time_btn)

            dias_tomando_view.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    // Lógica a ser executada quando o progresso da SeekBar é alterad
                    dias_texto.setText("dias tomando (${progress})")
                    dias_tomando=progress
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    // Lógica a ser executada quando o usuário começa a interagir com a SeekBar
                }
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    // Lógica a ser executada quando o usuário para de interagir com a SeekBar
                }
            })
            datapicker_btn.setOnClickListener {
                datapicker(firstFragment_fragmentmanager!!,data_texto,context)
            }
            timepicker_btn.setOnClickListener {
                timepicker(firstFragment_fragmentmanager!!,hora_texto)
            }

            checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
                if (isChecked) {
                    ultimo_dias_tomando=dias_tomando
                    dias_tomando=365
                    dias_tomando_view.setProgress(30,true)
                    dias_tomando_view.setOnTouchListener { view, motionEvent ->
                        true
                    }
                    dias_texto.setText("dias tomando (1 ano)")

                }else{
                    dias_texto.setText("dias tomando (${ultimo_dias_tomando})")
                    dias_tomando_view.setProgress(ultimo_dias_tomando,true)

                    dias_tomando_view.setOnTouchListener(null)

                }

            }
            botão_criar.setOnClickListener(){
                var nome = textinput.text.toString()
                var intervalo = 24.0

                var string=" valores padrões usados no: "


                if (!nome.isBlank()){



                        if (interval.text.toString().isEmpty()){
                            intervalo=24.0
                            string= string+" intervalo(24horas)"
                            string.plus("  ")
                        }else{
                            intervalo = interval.text.toString().toInt().toDouble()
                        }
                        if(dias_tomando==0){
                            dias_tomando=1
                            string = string+" dias tomando(1dia)"
                        }

                        var lembrete =  Lembrete(nome, intervalo, dias_tomando, calendar)
                        LembretesDao().adiciona_lembrete(
                           lembrete,
                            context
                        )
                        dialoa.dismiss()

                    if (interval.text.toString().isEmpty()  || calendar.equals(Calendar.getInstance()) || dias_tomando==0){
                        Toast.makeText(context,string, Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(context,"Nome Vazio",Toast.LENGTH_LONG).show();
                }
                ScreenManager.atualiza_lista(context)
            }
            botão_cancelar.setOnClickListener(){
                dialoa.dismiss()
            }
            dialoa.setView(view)
            dialoa.show()
        }

}