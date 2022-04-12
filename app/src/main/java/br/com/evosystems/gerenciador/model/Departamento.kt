package br.com.evosystems.gerenciador.model

import androidx.room.Entity

@Entity
data class Departamento(
    val id: Int,
    val nome: String,
    val sigla: String,
    val imagem: String? = null
)
