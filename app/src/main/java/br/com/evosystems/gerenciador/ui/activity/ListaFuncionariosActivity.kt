package br.com.evosystems.gerenciador.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.evosystems.gerenciador.databinding.ActivityListaFuncionarioActivityBinding
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.extensions.Toast
import br.com.evosystems.gerenciador.ui.recyclerview.adapter.ListaFuncionariosAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListaFuncionariosActivity : AppCompatActivity() {

    private val adapter = ListaFuncionariosAdapter(context = this, funcionarios = emptyList())

    var iDPROVISIORIO: String = "Deu errado"

    private val binding by lazy {
        ActivityListaFuncionarioActivityBinding.inflate(layoutInflater)
    }

    private val departamentoDao by lazy {
        AppDatabase.instancia(this).departamentoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            launch {

            }
            intent.getStringExtra("CHAVE_DEPARTAMENTO_ID")?.let { idDep ->
                departamentoDao.buscaDepPorId(idDep).collect {
                    Log.i("ListaFuncionarios", "onCreate: $it")
                }
            }
        }



        setContentView(binding.root)
        title = "Funcionários cadastrados"
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        val dbFunc = AppDatabase.instancia(this)
        val funcionarioDao = dbFunc.funcionarioDao()
        adapter.atualiza(funcionarioDao.buscaTodosFunc())
    }

    private fun configuraFab() {
        val fab = binding.activityListaFuncionariosFab
        fab.setOnClickListener {
            vaiParaFormularioFuncionario()
        }
    }

     private fun vaiParaFormularioFuncionario() {
        val intent = Intent(this, FormularioFuncionarioActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaFuncionariosRecyclerView
        recyclerView.adapter = adapter
        adapter.quandoClicaNoFuncionario = {
            val intent = Intent(
                this,
                DetalhesFuncionarioActivity::class.java
            ).apply {
                putExtra(CHAVE_FUNCIONARIO_ID, it)
            }
            startActivity(intent)
        }
    }
}