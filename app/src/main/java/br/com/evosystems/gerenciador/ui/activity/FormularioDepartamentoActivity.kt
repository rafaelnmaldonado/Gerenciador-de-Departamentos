package br.com.evosystems.gerenciador.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.evosystems.gerenciador.databinding.ActivityFormularioDepartamentoBinding
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.extensions.Toast
import br.com.evosystems.gerenciador.extensions.tentaCarregarImagem
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.model.Funcionario
import br.com.evosystems.gerenciador.ui.dialog.FormularioImagemDialog


class FormularioDepartamentoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioDepartamentoBinding.inflate(layoutInflater)
    }

    private val departamentoDao by lazy {
        AppDatabase.instancia(this).departamentoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar departamento"

        intent.getParcelableExtra<Departamento>(CHAVE_DEPARTAMENTO)?.let { departamento ->
            title = "Editar departamento ${departamento.nome}"
            preencheCampos(departamento)
            val botaoSalvar = binding.activityFormularioDepartamentoBotaoSalvar
            botaoSalvar.setOnClickListener {
                val depEditado = salvaEdicaoDep(departamento)
                departamentoDao.atualizaDep(depEditado)
                val tipoEdicao = "atualizado"
                voltaListaDep(departamento, tipoEdicao)
            }
        } ?: configuraBotaoSalvar()
    }

    private fun voltaListaDep(departamento: Departamento, tipo: String) {

        val intent = Intent(
            this,
            ListaDepartamentosActivity::class.java
        ).apply {
            Toast("Departamento ${departamento.nome} $tipo")
            Log.i("NomeDepEditado", "Funcionário: $departamento")
        }
        startActivity(intent)
    }

    private fun preencheCampos(departamento: Departamento) {
        with(binding) {
            Log.i("Detalhes", "$departamento")

            activityFormularioDepartamentoNome.setText(departamento.nome)
            activityFormularioDepartamentoSigla.setText(departamento.sigla)

        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioDepartamentoBotaoSalvar
        val dbDep = AppDatabase.instancia(this)
        val departamentoDao = dbDep.departamentoDao()
        botaoSalvar.setOnClickListener {
            val departamentoNovo = criaDepartamento()
            departamentoDao.salvaDep(departamentoNovo)
            finish()
        }
    }

    fun salvaEdicaoDep(departamento: Departamento): Departamento {
        val campoNome = binding.activityFormularioDepartamentoNome
        val nome = campoNome.text.toString()
        val campoSigla = binding.activityFormularioDepartamentoSigla
        val sigla = campoSigla.text.toString()

        return Departamento(id = departamento.id, nome = nome, sigla = sigla)
    }

    private fun criaDepartamento(): Departamento {
        val campoNome = binding.activityFormularioDepartamentoNome
        val nome = campoNome.text.toString()
        val campoSigla = binding.activityFormularioDepartamentoSigla
        val sigla = campoSigla.text.toString()

        return Departamento(nome = nome, sigla = sigla)
    }

}