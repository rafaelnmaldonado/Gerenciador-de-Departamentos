package br.com.evosystems.gerenciador.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.evosystems.departamento.databinding.ActivityFormularioFuncionarioBinding
import br.com.evosystems.gerenciador.dao.FuncionarioDao
import br.com.evosystems.gerenciador.extensions.tentaCarregarImagem
import br.com.evosystems.gerenciador.model.Funcionario
import br.com.evosystems.gerenciador.ui.dialog.FormularioImagemDialog


class FormularioFuncionarioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioFuncionarioBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar departamento"
        configuraBotaoSalvar()
        binding.activityFormularioFuncionarioImagem.setOnClickListener {
            FormularioImagemDialog(this)
                .mostra(url) { imagem ->
                    url = imagem
                    binding.activityFormularioFuncionarioImagem.tentaCarregarImagem(url)
                }
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioFuncionarioBotaoSalvar
        val dao = FuncionarioDao()
        botaoSalvar.setOnClickListener {
            val funcionarioNovo = criaFuncionario()
            dao.adiciona(funcionarioNovo)
            finish()
        }
    }

    private fun criaFuncionario(): Funcionario {
        val campoId = binding.activityFormularioFuncionarioId
        val idEmTexto = campoId.text.toString()
        val id = idEmTexto.toInt()
        val campoNome = binding.activityFormularioFuncionarioNome
        val nome = campoNome.text.toString()
        val campoRg = binding.activityFormularioFuncionarioRg
        val rgEmTexto = campoRg.text.toString()
        val rg = rgEmTexto.toInt()
        val campoDigito = binding.activityFormularioFuncionarioDigito
        val digito = campoDigito.text.toString()

        return Funcionario(id = id, nome = nome, _rg = rg, digitoRg = digito, foto = url)
    }

}