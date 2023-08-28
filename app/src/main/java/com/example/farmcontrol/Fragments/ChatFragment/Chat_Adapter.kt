package com.example.farmcontrol.Fragments.ChatFragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farmcontrol.R

class Chat_Adapter(var context: Context)  : RecyclerView.Adapter<Chat_Adapter.ViewHolder>(){
    var mensagens = ChatManager.mensagens
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflador = LayoutInflater.from(context)
        var view = inflador.inflate(R.layout.message_left, parent, false)
        Log.i("chat", "onCreateViewHolder: ${viewType}")
        when(viewType){
            1->{
                 view = inflador.inflate(R.layout.message_right, parent, false)
            }
            2->{
                 view = inflador.inflate(R.layout.message_left, parent, false)
            }
            3->{
                 view = inflador.inflate(R.layout.message_first, parent, false)

            }


        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position==0){
            holder.vincula(
                "Oi sou o Penk, estou integrado ao modelo chatgpt-3.5-turbo para tentar responder possiveis duvidas."
            )
        }else{
            holder.vincula(mensagens[position].Message)

        }
    }
    override fun getItemViewType(position: Int): Int {
        when(mensagens[position].Autor){
            "user"->{
                return 1
            }
            "chatgpt"->{
                return 2
            }
            "apresentation"->{
                return 3
            }
        }
        return 3
    }
    override fun getItemCount(): Int {
        Log.i("chatadpater", "getItemCount: ${mensagens.size}")
        return mensagens.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(texto: String) {
            var message = itemView.findViewById<TextView>(R.id.chat_message)
            message.setText(texto)
        }
    }

}