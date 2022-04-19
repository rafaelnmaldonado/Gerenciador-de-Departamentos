package br.com.evosystems.gerenciador.database.dao

import androidx.room.*
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.model.Funcionario
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartamentoDao {

    @Query("SELECT * FROM Departamento ORDER BY sigla, id")
    fun buscaTodosDep() : List<Departamento>

    @Query("SELECT nome FROM departamento WHERE id = :idDep")
    fun buscaNomeDep(idDep: Int) : String

    @Insert
    fun salvaDep(vararg departamento: Departamento)

    @Update
    fun atualizaDep(vararg departamento: Departamento)

    @Delete
    fun deletaDep(vararg departamento: Departamento)
}