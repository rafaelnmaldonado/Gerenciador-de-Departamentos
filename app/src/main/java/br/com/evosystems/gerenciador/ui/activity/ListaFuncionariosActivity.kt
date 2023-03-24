package br.com.evosystems.gerenciador.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.databinding.ActivityListaFuncionarioActivityBinding
import br.com.evosystems.gerenciador.ui.recyclerview.adapter.ListaFuncionariosAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListaFuncionariosActivity : AppCompatActivity() {

    private val adapter = ListaFuncionariosAdapter(context = this)

    private var nomeDepartamento: String = ""

    private val binding by lazy {
        ActivityListaFuncionarioActivityBinding.inflate(layoutInflater)
    }

    private val funcionarioDao by lazy {
        AppDatabase.instancia(this).funcionarioDao()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {

            intent.getStringExtra(CHAVE_DEPARTAMENTO_NOME)?.let { nomeDep ->
                nomeDepartamento = nomeDep
            } ?: Log.i("Usuários", "Nome Departamento: Nulo")

            intent.getStringExtra(CHAVE_DEPARTAMENTO_ID)?.let { idDep ->
                funcionarioDao.buscaPorIdDep(idDep).collect {
                    funcionarioDao.buscaPorIdDep(idDep).collect { funcionarios ->
                        adapter.atualiza(funcionarios)
                        configuraRecyclerView()
                        configuraFab(idDep)
                        Log.i("Usuários", "ID Departamento: $idDep")
                        Log.i("Usuários", "Usuários no departamento: $it")
                    }
                }
            } ?: Log.i("Usuários", "ID Departamento: Nulo")
        }

        configuraVoltar()
        setContentView(binding.root)
        title = nomeDepartamento
    }

    private fun configuraFab(idDep: String) {
        val fab = binding.activityListaFuncionariosFab
        fab.setOnClickListener {
            vaiParaFormularioFuncionario(idDep)
        }
    }

    private fun configuraVoltar() {
        val volta = binding.activityListaFuncionariosVoltar
        volta.setOnClickListener {
            val intent = Intent(
                this,
                ListaDepartamentosActivity::class.java
            )
            startActivity(intent)
        }
    }

     private fun vaiParaFormularioFuncionario(idDep: String) {
        val intent = Intent(
            this,
            FormularioFuncionarioActivity::class.java
        ).apply {
            putExtra(CHAVE_DEPARTAMENTO_ID, idDep)
        }
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
                putExtra(CHAVE_FUNCIONARIO, it)
                Log.i("DetalhesFuncionário", "Recycler funcionário: $it")
            }
            startActivity(intent)
        }
    }
}