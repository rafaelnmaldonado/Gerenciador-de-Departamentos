package br.com.evosystems.gerenciador.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Funcionario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val idDep: Int,
    val nome: String,
    var foto: String? = null,
    val _rg: Int,
    val digitoRg: String
) : Parcelable {
    var rgFormatado = ""
    val rg: String
        get() {
            if ((this._rg / 100000000) >= 1 || (this._rg / 1000000 < 1))
            {
                rgFormatado = "RG inválido"
            }
            else
            {
                var rg1 = _rg - (_rg % 1000000)
                var rg2 = _rg - rg1 - (_rg % 1000)
                var rg3 = _rg - rg1 - rg2
                rg1 /= 1000000
                rg2 /= 1000
                rgFormatado = "$rg1.$rg2.$rg3-$digitoRg"
            }

            return rgFormatado
        }
}