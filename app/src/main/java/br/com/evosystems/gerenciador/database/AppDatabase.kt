package br.com.evosystems.gerenciador.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.evosystems.gerenciador.database.dao.DepartamentoDao
import br.com.evosystems.gerenciador.database.dao.FuncionarioDao
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.model.Funcionario

@Database(entities = [Departamento::class, Funcionario::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun departamentoDao(): DepartamentoDao

    abstract fun funcionarioDao(): FuncionarioDao

    companion object {
        fun instanciaDep(context: Context) : AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "departamento.db"
            ).allowMainThreadQueries().build()
        }

        fun instanciaFunc(context: Context) : AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "funcionario.db"
            ).allowMainThreadQueries().build()
        }
    }
}