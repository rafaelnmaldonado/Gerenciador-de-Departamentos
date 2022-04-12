package br.com.evosystems.gerenciador.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.evosystems.gerenciador.dao.DepartamentoDao
import br.com.evosystems.gerenciador.dao.FuncionarioDao
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.model.Funcionario

@Database(entities = [Departamento::class, Funcionario::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun departamentoDao(): DepartamentoDao
    abstract fun funcionarioDao(): FuncionarioDao
}