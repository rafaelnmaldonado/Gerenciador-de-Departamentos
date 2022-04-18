package br.com.evosystems.gerenciador.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.evosystems.gerenciador.model.Departamento
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartamentoDao {

    @Query("SELECT * FROM Departamento ORDER BY id DESC")
    fun buscaTodosDep() : List<Departamento>

    @Insert
    fun salvaDep(vararg departamento: Departamento)
}