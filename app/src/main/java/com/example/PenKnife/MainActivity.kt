package com.example.PenKnife

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.PenKnife.Telas.TelaLembretes.FirstFragment
import com.example.PenKnife.Telas.TelaFunções.SecondFragment
import com.example.PenKnife.Notificações.Services.permissãoDialog
import com.example.PenKnife.logica.Database.LembretesAgendados.DatabaseApp
import com.example.PenKnife.logica.Database.LembretesAgendados.Models.LembreteModel
import com.example.PenKnife.Telas.TelaLembretes.Lembretes.LembretesDAO
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onResume() {
        super.onResume()
        titleColor=R.color.cor_de_letra
        window.statusBarColor=getColor(R.color.cor_de_letra)
       window
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var FragmentoAtivo: Fragment? =SecondFragment()

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.container, FragmentoAtivo!!) // adiciona o Fragment ao contêiner
        fragmentTransaction.commit() // confirma a transação
        LembretesDAO().load_lembretes(this)


        permissãoDialog(this)
        fun databaseload(){
            val db = Room.databaseBuilder(
                applicationContext,
                DatabaseApp::class.java, "lembretes"
            ).build()
            val userDao = db.lembreteDao()
            val lembretes: List<LembreteModel> = userDao.getAll()
            Log.i("database", "databaseload: ${lembretes} ")

        }
        CoroutineScope(Dispatchers.Default).launch{
            try {
                databaseload()
            }catch (e:Exception){
                
            }

        }

        tabLayout.selectTab(tabLayout.getTabAt(1))
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

                val fragmentTransaction = fragmentManager.beginTransaction()
                when(tab!!.position){
                    0->{
                        FragmentoAtivo= FirstFragment()
                    }
                    1->{
                        FragmentoAtivo= SecondFragment()
                    }
                    2->{
//
                    }
                }
                fragmentTransaction.add(R.id.container, FragmentoAtivo!!) // adiciona o Fragment ao contêiner
                fragmentTransaction.commit() // confirma a transação
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
               if(FragmentoAtivo!=null){
                   fragmentManager.beginTransaction().remove(FragmentoAtivo!!).commit()
               }
            }


            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })


    }

}






