package br.com.evosystems.gerenciador.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.evosystems.gerenciador.model.Departamento

@Dao
interface DepartamentoDao {

    @Query("SELECT * FROM Departamento")
    fun buscaTodosDep() : List<Departamento>

    @Insert
    fun salvaDep(vararg departamento: Departamento)
}