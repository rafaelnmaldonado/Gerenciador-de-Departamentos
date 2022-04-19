package br.com.evosystems.gerenciador.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.model.Funcionario
import br.com.evosystems.gerenciador.databinding.ActivityFuncionarioDetalhesBinding
import br.com.evosystems.gerenciador.extensions.tentaCarregarImagem

class DetalhesFuncionarioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFuncionarioDetalhesBinding.inflate(layoutInflater)
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
            setEditarFunc(funcionarioCarregado)
            preencheCampos(funcionarioCarregado)
        } ?: finish()
    }

    private fun preencheCampos(funcionarioCarregado: Funcionario) {
        Log.i("Detalhes", "Tenta preencher")
        with(binding) {
            activityFuncionarioDetalhesFoto.tentaCarregarImagem(funcionarioCarregado.foto)
            //activityFuncionarioDetalhesNome.text = funcionarioCarregado.nome
            //activityFuncionarioDetalhesRg.text = funcionarioCarregado.rg
        }
    }

    private fun setEditarFunc(funcionarioCarregado: Funcionario) {
        val intent = Intent(
            this,
            FormularioFuncionarioActivity::class.java
        ).apply {
            putExtra(CHAVE_FUNCIONARIO, funcionarioCarregado)
            Log.i("DetalhesFuncionário", "Recycler funcionário: $funcionarioCarregado")
        }
        //startActivity(intent)
    }
}