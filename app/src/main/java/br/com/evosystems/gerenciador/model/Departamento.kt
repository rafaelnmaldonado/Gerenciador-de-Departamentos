package br.com.evosystems.gerenciador.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Departamento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val sigla: String,
    val imagem: String? = null
) : Parcelable
