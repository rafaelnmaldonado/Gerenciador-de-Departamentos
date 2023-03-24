package br.com.evosystems.gerenciador.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Funcionario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    var foto: String? = null,
    val _rg: Int,
    val digitoRg: String,
    val idDep: Int? = null
) : Parcelable {
    @IgnoredOnParcel
    var rgFormatado = ""
    val rg: String
        get() {
            if ((this._rg / 100000000) >= 1) {
                rgFormatado = "RG inválido"
            }
            else {
                var charRg1 = this._rg - (this._rg % 10000000)
                var charRg2 = this._rg - charRg1 - (this._rg % 1000000)
                var charRg3 = this._rg - charRg1 - charRg2 - (this._rg % 100000)
                var charRg4 = this._rg - charRg1 - charRg2 - charRg3 - (this._rg % 10000)
                var charRg5 = this._rg - charRg1 - charRg2 - charRg3 - charRg4 - (this._rg % 1000)
                var charRg6 = this._rg - charRg1 - charRg2 - charRg3 - charRg4 - charRg5 - (this._rg % 100)
                var charRg7 = this._rg - charRg1 - charRg2 - charRg3 - charRg4 - charRg5 - charRg6 - (this._rg % 10)
                val charRg8 = this._rg - charRg1 - charRg2 - charRg3 - charRg4 - charRg5 - charRg6 - charRg7
                charRg1 /= 10000000
                charRg2 /= 1000000
                charRg3 /= 100000
                charRg4 /= 10000
                charRg5 /= 1000
                charRg6 /= 100
                charRg7 /= 10

                val restoRg = (charRg1*9 + charRg2*8 + charRg3*7 + charRg4*6 + charRg5*5 + charRg6*4 + charRg7*3 + charRg8*2) % 11

                val digitoProvRg: String

                if (restoRg == 10) digitoProvRg = "X"

                else digitoProvRg = restoRg.toString()


                if (digitoRg == digitoProvRg) {
                    var rg1 = _rg - (_rg % 1000000)
                    var rg2 = _rg - rg1 - (_rg % 1000)
                    var rg3 = _rg - rg1 - rg2
                    rg1 /= 1000000
                    rg2 /= 1000
                    if (rg1 != 0) rgFormatado = "$rg1.$rg2.$rg3-$digitoRg"
                    else if (rg2 != 0) rgFormatado = "$rg2.$rg3-$digitoRg"
                    else rgFormatado = "$rg3-$digitoRg"
                }
                else rgFormatado = "RG inválido"
            }
            return rgFormatado
        }
}