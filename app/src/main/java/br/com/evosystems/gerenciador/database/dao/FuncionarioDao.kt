package br.com.evosystems.gerenciador.database.dao

import androidx.room.*
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.model.Funcionario
import kotlinx.coroutines.flow.Flow

@Dao
interface FuncionarioDao {

    @Query("SELECT * FROM Funcionario")
    fun buscaTodosFunc() : Flow<List<Funcionario>>

    @Insert
    fun salvaFunc(vararg funcionario: Funcionario)

    @Query("SELECT * FROM Funcionario WHERE idDep = :idDep")
    fun buscaPorIdDep(idDep: String): Flow<List<Funcionario>>

    @Update
    fun atualizaFunc(vararg funcionario: Funcionario)

    @Delete
    fun deletaFunc(vararg funcionario: Funcionario)

    @Query("DELETE from Funcionario WHERE idDep = :idDep")
    fun deletaFuncDep(idDep: Int)
}