package com.example.PenKnife.Telas.ChatFrag.ChatGptManager

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.PenKnife.Telas.ChatFrag.ChatGpt.ChatView
import com.example.PenKnife.Telas.ChatFrag.ClassesModelos.ChatGptAswner
import com.example.PenKnife.Telas.ChatFrag.ClassesModelos.Corpo
import com.example.PenKnife.Telas.ChatFrag.ClassesModelos.MessageBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ChatGptApi (){
    fun getRetrofitInstance(path : String) : Retrofit {

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60*10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(path)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
     fun pergunta_ao_chat_gpt(context: Context, message:String,lista:RecyclerView): String? {

        var resposta:String="falha ao responder"
        CoroutineScope(Dispatchers.Main).launch {


            val url = "https://4d6b-179-191-213-240.sa.ngrok.io"
            val url_ofc = "https://api.openai.com/"
            val retrofitClient = getRetrofitInstance(url_ofc)
            val endpoint = retrofitClient.create(EndPoints.Endpoint::class.java)
            val messages = listOf<MessageBody>(MessageBody(message,"user"))
            val body =  Corpo("gpt-3.5-turbo",messages,0.7)
            //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
          val apikey = "Bearer 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"
     //     api code
            //  val apikey = "Bearer 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"
            val contentType = "application/json"
            val call = endpoint.pergunta_ao_chat(body, contentType, apikey)

            withContext(Dispatchers.Main){
                ChatView.carrega_mensagem("chatgpt",context,lista)
            }
            withContext(Dispatchers.Default){
                var resposta_da_api: Response<ChatGptAswner> = call.execute()

                Log.i("web", "getData: ${resposta_da_api}")
                Log.i("web", "getData: ${resposta_da_api?.body()}")
                Log.i("web", "getData: ${resposta_da_api?.body()?.choices}")
                Log.i("web", "getData: ${resposta_da_api?.body()?.choices?.get(0)}")
                Log.i("web", "getData: ${resposta_da_api?.body()?.choices?.get(0)?.message}")
                Log.i("web", "getData: ${resposta_da_api?.body()?.choices?.get(0)?.message?.content}")
                resposta = resposta_da_api?.body()?.choices?.get(0)?.message?.content.toString()
            }

           withContext(Dispatchers.Main){
               Log.i("web", "pergunta_ao_chat_gpt: adicionada ")
               ChatView.carregou=false
               delay(1000)
               if (resposta==null){
                   ChatView.finish_load("falha na resposta, por favor tente mais tarde","chatgpt",context,lista)
               }else{
                   ChatView.finish_load(resposta,"chatgpt",context,lista)

               }
               ChatView.atualiza_lista(context,lista)
           }



        }
        return  resposta





}
}