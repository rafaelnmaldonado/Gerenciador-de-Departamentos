package br.com.evosystems.gerenciador.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.evosystems.gerenciador.model.Funcionario

@Dao
interface FuncionarioDao {

    @Query("SELECT * FROM Funcionario")
    fun buscaTodos() : List<Funcionario>

    @Insert
    fun salva(funcionario: Funcionario)
}