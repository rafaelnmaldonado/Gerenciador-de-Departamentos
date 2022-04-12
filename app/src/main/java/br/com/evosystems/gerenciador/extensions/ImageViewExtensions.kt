package br.com.evosystems.gerenciador.extensions

import android.widget.ImageView
import br.com.evosystems.departamento.R
import coil.load

fun ImageView.tentaCarregarImagem(url: String? = null){
    load(url) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}