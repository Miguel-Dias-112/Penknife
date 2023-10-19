package com.example.farmcontrol

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.farmcontrol.Fragments.ChatFrag.ThirdFragment
import com.example.farmcontrol.Fragments.LembretesAgendadosFrag.FirstFragment
import com.example.farmcontrol.Fragments.HomeFrag.SecondFragment
import com.example.farmcontrol.logica.Services.permissãoDialog
import com.example.farmcontrol.logica.Database.LembretesAgendados.DatabaseApp
import com.example.farmcontrol.logica.Database.LembretesAgendados.Models.LembreteModel
import com.example.farmcontrol.logica.LembretesDao
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

        var myFragment: Fragment? =SecondFragment()

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, myFragment!!) // adiciona o Fragment ao contêiner
        fragmentTransaction.commit() // confirma a transação
        LembretesDao().load_lembretes(this)


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
                        myFragment= FirstFragment()
                    }
                    1->{
                        myFragment= SecondFragment()
                    }
                    2->{
                        myFragment= ThirdFragment()
                    }
                }
                fragmentTransaction.add(R.id.container, myFragment!!) // adiciona o Fragment ao contêiner
                fragmentTransaction.commit() // confirma a transação
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
               if(myFragment!=null){
                   fragmentManager.beginTransaction().remove(myFragment!!).commit()
               }
            }


            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })


    }

}






