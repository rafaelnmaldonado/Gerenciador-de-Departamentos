package br.com.evosystems.gerenciador.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Departamento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val sigla: String,
    val imagem: String? = null
)
