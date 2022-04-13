package br.com.evosystems.gerenciador.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.evosystems.gerenciador.model.Departamento

@Dao
interface DepartamentoDao {

    @Query("SELECT * FROM Departamento")
    fun buscaTodos() : List<Departamento>

    @Insert
    fun salva(vararg departamento: Departamento)
}