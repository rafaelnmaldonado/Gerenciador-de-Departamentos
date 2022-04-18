package br.com.evosystems.gerenciador.extensions

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.vaiPara(
    classe: Class<*>,
    intent: Intent.() -> Unit = {}
) {
    Intent(this, classe)
        .apply {
            intent()
            startActivity(this)
        }
}

fun Context.Toast(mensagem: String) {
    Toast.makeText(
        this,
        mensagem,
        Toast.LENGTH_SHORT
    ).show()
}