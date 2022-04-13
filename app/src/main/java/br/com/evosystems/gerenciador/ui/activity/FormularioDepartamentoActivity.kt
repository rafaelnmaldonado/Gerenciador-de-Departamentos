package br.com.evosystems.gerenciador.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.com.evosystems.gerenciador.databinding.ActivityFormularioDepartamentoBinding
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.extensions.tentaCarregarImagem
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.ui.dialog.FormularioImagemDialog


class FormularioDepartamentoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioDepartamentoBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar departamento"
        configuraBotaoSalvar()
        binding.activityFormularioDepartamentoImagem.setOnClickListener {
            FormularioImagemDialog(this)
                .mostra(url) { imagem ->
                    url = imagem
                    binding.activityFormularioDepartamentoImagem.tentaCarregarImagem(url)
                }
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioDepartamentoBotaoSalvar
        val dbDep = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "departamento.db"
        ).allowMainThreadQueries().build()
        val departamentoDao = dbDep.departamentoDao()
        botaoSalvar.setOnClickListener {
            val departamentoNovo = criaDepartamento()
            departamentoDao.salva(departamentoNovo)
            finish()
        }
    }

    private fun criaDepartamento(): Departamento {
        val campoId = binding.activityFormularioDepartamentoId
        val idEmTexto = campoId.text.toString()
        val id = idEmTexto.toInt()
        val campoNome = binding.activityFormularioDepartamentoNome
        val nome = campoNome.text.toString()
        val campoSigla = binding.activityFormularioDepartamentoDescricao
        val sigla = campoSigla.text.toString()

        return Departamento(id = id, nome = nome, sigla = sigla, imagem = url)
    }

}