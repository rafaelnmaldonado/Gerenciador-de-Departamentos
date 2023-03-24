package br.com.evosystems.gerenciador.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.evosystems.gerenciador.database.dao.DepartamentoDao
import br.com.evosystems.gerenciador.database.dao.FuncionarioDao
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.model.Funcionario

@Database(entities = [Departamento::class, Funcionario::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun departamentoDao(): DepartamentoDao

    abstract fun funcionarioDao(): FuncionarioDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null
        fun instancia(context: Context) : AppDatabase {
            return db?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "gerenciador.db"
            ).allowMainThreadQueries().build().also {
                db = it
            }
        }
    }
}