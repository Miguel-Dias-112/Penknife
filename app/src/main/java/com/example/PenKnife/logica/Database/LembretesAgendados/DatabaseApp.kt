package com.example.PenKnife.logica.Database.LembretesAgendados

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.PenKnife.logica.Database.LembretesAgendados.Models.LembreteModel

@Database(entities = [LembreteModel::class], version =3)
abstract class DatabaseApp : RoomDatabase() {
    abstract fun lembreteDao(): DatabaseDao
}
