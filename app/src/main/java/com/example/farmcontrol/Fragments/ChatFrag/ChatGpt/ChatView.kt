package com.example.farmcontrol.Fragments.ChatFrag.ChatGpt

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmcontrol.R
import com.example.farmcontrol.Fragments.ChatFrag.ChatGptManager.ChatGptApi
import com.example.farmcontrol.Fragments.ChatFrag.ClassesModelos.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatView(var view:View, var context: Context) {
    val cicloDeVida = CoroutineScope(Dispatchers.Main)

    init {
        var chatlist: RecyclerView = view.findViewById(R.id.chat_list)
        if (mensagens.size<1){ adicionar_mensagem("teste1","apresentation",context, chatlist!!) }
        atualiza_lista(context,chatlist)

        cicloDeVida.launch {
            val  botao_enviar = view.findViewById<ImageButton>(R.id.send_btn)
            val  texto_Input = view.findViewById<EditText>(R.id.text_input)


            botao_enviar.setOnClickListener {
                cicloDeVida.launch {

                    val pergunta = texto_Input.text.toString()
                    perguntarChatGPT(context, chatlist,pergunta)
                    texto_Input.setText("")

                }

            }
        }
    }
    companion object{
        var carregou=false
        var index: Int? = null
        var recyclerView: RecyclerView? = null


        suspend fun perguntarChatGPT(context: Context, recyclerView: RecyclerView, pergunta:String){
            val mensagemÑVazia = !pergunta.isBlank()
            val estácarregando= !carregou
            Log.i("chat", "perguntarChatGPT: ${mensagemÑVazia} and ${estácarregando}")

            if (mensagemÑVazia and estácarregando){
                adicionar_mensagem(pergunta,"user",context,recyclerView)
                var resposta = ChatGptApi().pergunta_ao_chat_gpt(context,pergunta,recyclerView)
                Log.i("chat", "perguntarChatGPT: 2")

            }

            //if (resposta != null) {
             //   adicionar_mensagem(resposta,"chatgpt",context,recyclerView)
            //}

        }

        fun atualiza_lista(context:Context,recyclerView: RecyclerView){
            var chatlist: RecyclerView? = recyclerView
            chatlist?.setLayoutManager(LinearLayoutManager(context))
            var adapter = Chat_Adapter(context)
            chatlist?.adapter=adapter
            chatlist?.smoothScrollToPosition(adapter.itemCount-1)
        }
        fun atualiza_lista_without_Scrool(context:Context,recyclerView: RecyclerView){
            var chatlist: RecyclerView? = recyclerView
            chatlist?.setLayoutManager(LinearLayoutManager(context))
            var adapter = Chat_Adapter(context)
            chatlist?.adapter=adapter
        }
        fun adicionar_mensagem(texto:String,autor:String,context: Context,recyclerView: RecyclerView): Message {
            var mensagem= Message(texto,autor)
            mensagens.add(mensagem)
            atualiza_lista_without_Scrool(context,recyclerView)
            return mensagem
        }


        fun carrega_mensagem(autor:String,context: Context,recyclerView: RecyclerView){
            val message = adicionar_mensagem("loading",autor,context,recyclerView)
            index = mensagens.indexOf(message)

            carregou =true
            CoroutineScope(Dispatchers.Main).launch {
                Log.i("carregou", "carrega_me $carregou")
                while (carregou){
                    mensagens[index!!]= Message("loading.",autor)
                    atualiza_lista_without_Scrool(context,recyclerView)
                    delay(500)
                    mensagens[index!!]= Message("loading..",autor)
                    atualiza_lista_without_Scrool(context,recyclerView)
                    delay(500)
                    mensagens[index!!]= Message("loading...",autor)
                    atualiza_lista_without_Scrool(context,recyclerView)
                    delay(500)
                }

            }

        }

        fun finish_load (texto:String,autor:String,context: Context,recyclerView: RecyclerView){
            mensagens[index!!]= Message(texto,autor)
            carregou =false
            atualiza_lista_without_Scrool(context,recyclerView)
        }
        var mensagens:MutableList<Message> = mutableListOf()
    }
}