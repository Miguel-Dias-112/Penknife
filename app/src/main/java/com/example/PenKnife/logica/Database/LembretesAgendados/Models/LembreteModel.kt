package com.example.PenKnife.logica.Database.LembretesAgendados.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "lembretes")

data class LembreteModel(
    @PrimaryKey val id: Int,
    //@ColumnInfo(name = "data_inicial") var data_inicial: Calendar,
    @ColumnInfo(name = "dias_tomando") var _dias_tomando: Int,
    @ColumnInfo(name = "time_interval") var intervalo_de_tempo_horas: Float,
    @ColumnInfo(name = "titulo") var titulo: String,
    @ColumnInfo(name = "ano") var ano: Int,
    @ColumnInfo(name = "mes") var mes: Int,

    @ColumnInfo(name = "dia") var dia: Int,
    @ColumnInfo(name = "hora") var hora: Int,
    @ColumnInfo(name = "minuto") var minuto: Int,


    )