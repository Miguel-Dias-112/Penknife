package com.example.PenKnife.Notificações.Services

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import com.example.PenKnife.Telas.TelaFunções.Views.Blocker.BlockerService
import com.example.PenKnife.Telas.TelaFunções.Views.Pomodoro.PomodoroService
import com.example.PenKnife.R
import com.google.android.material.button.MaterialButton

@SuppressLint("MissingInflatedId")
class permissãoDialog(var context: Context) {

    val REQUEST_CODE_OVERLAY_PERMISSION = 1001


    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.pede_sobreposicao, null)
        val dialog = AlertDialog.Builder(context).create()
        val btn=view.findViewById<MaterialButton>(R.id.permissao_btn)
       // if (true && !Settings.canDrawOverlays(context)) { } else { dialog.dismiss() }
        btn.setOnClickListener{
            if (true && !Settings.canDrawOverlays(context)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$context.packageName")
                )
                context.startActivity(intent)

            } else {
                context.startService(Intent(context, BlockerService::class.java))
            }
            dialog.dismiss()
        }


        dialog.setView(view)
        dialog.show()

        if (true && !Settings.canDrawOverlays(context)) {} else {
            dialog.dismiss()
            context.startService(Intent(context, PomodoroService::class.java))
            context.startService(Intent(context, BlockerService::class.java))
        }
    }

}

