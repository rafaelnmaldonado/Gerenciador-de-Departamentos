package br.com.evosystems.gerenciador.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.databinding.ActivityFormularioFuncionarioBinding
import br.com.evosystems.gerenciador.extensions.tentaCarregarImagem
import br.com.evosystems.gerenciador.model.Funcionario
import br.com.evosystems.gerenciador.ui.dialog.FormularioImagemDialog
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FormularioFuncionarioActivity : AppCompatActivity() {

    private val funcionarioDao by lazy {
        AppDatabase.instancia(this).funcionarioDao()
    }

    private val binding by lazy {
        ActivityFormularioFuncionarioBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            intent.getStringExtra(CHAVE_DEPARTAMENTO_ID)?.let { idDep ->
                funcionarioDao.buscaPorIdDep(idDep).collect {
                    configuraBotaoSalvar(idDep)
                }
            }
        }

        setContentView(binding.root)
        title = "Cadastrar funcionário"

        binding.activityFormularioFuncionarioImagem.setOnClickListener {
            FormularioImagemDialog(this)
                .mostra(url) { imagem ->
                    url = imagem
                    binding.activityFormularioFuncionarioImagem.tentaCarregarImagem(url)
                }
        }
    }

    private fun configuraBotaoSalvar(idDep: String) {
        val botaoSalvar = binding.activityFormularioFuncionarioBotaoSalvar
        val dbFunc = AppDatabase.instancia(this)
        val funcionarioDao = dbFunc.funcionarioDao()
        botaoSalvar.setOnClickListener {
            val funcionarioNovo = criaFuncionario(idDep)
            funcionarioDao.salvaFunc(funcionarioNovo)
            finish()
        }
    }

    private fun criaFuncionario(idDep: String): Funcionario {
        val campoNome = binding.activityFormularioFuncionarioNome
        val nome = campoNome.text.toString()
        val campoRg = binding.activityFormularioFuncionarioRg
        val rgEmTexto = campoRg.text.toString()
        val rg = rgEmTexto.toInt()
        val campoDigito = binding.activityFormularioFuncionarioDigito
        val digito = campoDigito.text.toString()

        return Funcionario(nome = nome, _rg = rg, digitoRg = digito, foto = url, idDep = idDep.toInt())
    }

}