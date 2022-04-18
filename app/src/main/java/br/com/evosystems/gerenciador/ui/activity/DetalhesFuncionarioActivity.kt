package br.com.evosystems.gerenciador.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.evosystems.gerenciador.model.Funcionario
import br.com.evosystems.gerenciador.databinding.ActivityDetalhesFuncionarioBinding
import br.com.evosystems.gerenciador.extensions.tentaCarregarImagem

private const val TAG = "DetalhesFuncionario"

class DetalhesFuncionarioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesFuncionarioBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarFuncionario()
    }

    private fun tentaCarregarFuncionario() {
        intent.getParcelableExtra<Funcionario>(CHAVE_DEPARTAMENTO_ID)?.let { funcionarioCarregado ->
            preencheCampos(funcionarioCarregado)
        } ?: finish()
    }

    private fun preencheCampos(funcionarioCarregado: Funcionario) {
        with(binding) {
            activityDetalhesFuncionarioFoto.tentaCarregarImagem(funcionarioCarregado.foto)
            activityDetalhesFuncionarioNome.text = funcionarioCarregado.nome
            activityDetalhesFuncionarioRg.text = funcionarioCarregado.rg
        }
    }
}