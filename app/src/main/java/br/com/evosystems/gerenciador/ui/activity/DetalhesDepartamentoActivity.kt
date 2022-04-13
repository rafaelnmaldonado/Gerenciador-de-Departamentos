package br.com.evosystems.gerenciador.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.databinding.ActivityDetalhesDepartamentoBinding
import br.com.evosystems.gerenciador.extensions.tentaCarregarImagem

class DetalhesDepartamentoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetalhesDepartamentoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarDepartamento()
    }

    private fun tentaCarregarDepartamento() {
        intent.getParcelableExtra<Departamento>(CHAVE_DEPARTAMENTO)?.let { departamentoCarregado ->
            preencheCampos(departamentoCarregado)
        } ?: finish()
    }

    private fun preencheCampos(departamentoCarregado: Departamento) {
        with(binding) {
            activityDetalhesDepartamentoImagem.tentaCarregarImagem(departamentoCarregado.imagem)
            activityDetalhesDepartamentoNome.text = departamentoCarregado.nome
            activityDetalhesDepartamentoSigla.text = departamentoCarregado.sigla
        }
    }
}