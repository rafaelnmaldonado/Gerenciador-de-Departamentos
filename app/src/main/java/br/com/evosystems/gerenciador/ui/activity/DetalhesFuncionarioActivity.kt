package br.com.evosystems.gerenciador.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.databinding.ActivityFuncionarioDetalhesBinding
import br.com.evosystems.gerenciador.extensions.Toast
import br.com.evosystems.gerenciador.extensions.tentaCarregarImagem
import br.com.evosystems.gerenciador.model.Funcionario
import br.com.evosystems.gerenciador.ui.dialog.FormularioImagemDialog

class DetalhesFuncionarioActivity : AppCompatActivity() {

    val context = this

    private val binding by lazy {
        ActivityFuncionarioDetalhesBinding.inflate(layoutInflater)
    }

    private val departamentoDao by lazy {
        AppDatabase.instancia(this).departamentoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Detalhes", "No arquivo")
        setContentView(binding.root)
        tentaCarregarFuncionario()
    }

    private fun tentaCarregarFuncionario() {
        intent.getParcelableExtra<Funcionario>(CHAVE_FUNCIONARIO)?.let { funcionarioCarregado ->
            title = "Detalhes funcionário ${funcionarioCarregado.nome}"
            preencheCampos(funcionarioCarregado)
        } ?: finish()
    }

    private fun preencheCampos(funcionarioCarregado: Funcionario) {
        with(binding) {
            Log.i("Detalhes", "$funcionarioCarregado")

            activityFuncionarioDetalhesNome.setText(funcionarioCarregado.nome)
            activityFuncionarioDetalhesRg.setText(funcionarioCarregado._rg.toString())
            activityFuncionarioDetalhesDigito.setText(funcionarioCarregado.digitoRg)

            activityFuncionarioDetalhesFoto.tentaCarregarImagem(funcionarioCarregado.foto)

            binding.activityFuncionarioDetalhesFoto.setOnClickListener {
                FormularioImagemDialog(context)
                    .mostra(funcionarioCarregado.foto) { imagem ->
                        funcionarioCarregado.foto = imagem
                        binding.activityFuncionarioDetalhesFoto.tentaCarregarImagem(funcionarioCarregado.foto)
                    }
            }

            configuraBotaoExcluir(funcionarioCarregado)
            configuraBotaoSalvar(funcionarioCarregado)
        }
    }

    private fun configuraBotaoExcluir(funcionario: Funcionario) {
        val botaoExcluir = binding.activityFuncionarioDetalhesBotaoExcluir
        val dbFunc = AppDatabase.instancia(this)
        val funcionarioDao = dbFunc.funcionarioDao()
        botaoExcluir.setOnClickListener {
            val tipoEdicao = "excluído(a)"
            funcionarioDao.deletaFunc(funcionario)
            voltaListaFunc(funcionario, tipoEdicao)
            finish()
        }
    }

    private fun configuraBotaoSalvar(funcionario: Funcionario) {
        val botaoSalvar = binding.activityFuncionarioDetalhesBotaoSalvar
        val dbFunc = AppDatabase.instancia(this)
        val funcionarioDao = dbFunc.funcionarioDao()
        botaoSalvar.setOnClickListener {
            val funcEditado = salvaEdicaoFunc(funcionario)
            funcionarioDao.atualizaFunc(funcEditado)
            val tipoEdicao = "atualizado(a)"
            voltaListaFunc(funcEditado, tipoEdicao)
            finish()
        }
    }

    private fun salvaEdicaoFunc(funcionario: Funcionario) : Funcionario {

        val campoNome = binding.activityFuncionarioDetalhesNome
        val nome = campoNome.text.toString()
        val campoRg = binding.activityFuncionarioDetalhesRg
        val rgEmTexto = campoRg.text.toString()
        val rg = rgEmTexto.toInt()
        val campoDigito = binding.activityFuncionarioDetalhesDigito
        val digito = campoDigito.text.toString()

        return Funcionario(id = funcionario.id, nome = nome, _rg = rg, digitoRg = digito, foto = funcionario.foto, idDep = funcionario.idDep?.toInt())
    }

    private fun voltaListaFunc(funcionario: Funcionario, tipo: String) {

        val nomeDep = departamentoDao.buscaNomeDep(funcionario.idDep!!)

        val intent = Intent(
            this,
            ListaFuncionariosActivity::class.java
        ).apply {
            Toast("Funcionário(a) ${funcionario.nome} $tipo")
            putExtra(CHAVE_DEPARTAMENTO_ID, funcionario.idDep.toString())
            putExtra(CHAVE_DEPARTAMENTO_NOME, nomeDep)
            Log.i("NomeFuncEditado", "Funcionário: $funcionario")
        }
        startActivity(intent)
    }
}