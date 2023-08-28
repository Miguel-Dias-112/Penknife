package com.example.farmcontrol.logica.Database.LembretesAgendados

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.farmcontrol.logica.Database.LembretesAgendados.Models.LembreteModel

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM lembretes")
    fun getAll(): List<LembreteModel>



    @Query("SELECT * FROM lembretes WHERE titulo")
    fun findByName(): LembreteModel

    @Insert
    fun insertAll(vararg lembrete: LembreteModel)
    @Query("DELETE FROM lembretes WHERE id = :id")
    fun deleteByUserId(id: Int)
    @Delete
    fun delete(user: LembreteModel)
}
