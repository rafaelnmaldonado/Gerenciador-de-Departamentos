package br.com.evosystems.gerenciador.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Departamento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name="nome") val nome: String,
    @ColumnInfo(name="sigla") val sigla: String
) : Parcelable
