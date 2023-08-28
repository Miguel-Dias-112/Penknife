package com.example.farmcontrol.logica.Database.LembretesPontuais


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.farmcontrol.logica.Database.LembretesAgendados.DatabaseDao
import com.example.farmcontrol.logica.Database.LembretesAgendados.Models.LembreteModel

@Database(entities = [LembreteModel::class], version =1)
abstract class DatabaseApp : RoomDatabase() {
    abstract fun lembreteDao(): DatabaseDao
}
