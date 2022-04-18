package br.com.evosystems.gerenciador.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.evosystems.gerenciador.databinding.ActivityListaDepartamentoActivityBinding
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.ui.recyclerview.adapter.ListaDepartamentosAdapter

class ListaDepartamentosActivity : AppCompatActivity() {

    private val adapter = ListaDepartamentosAdapter(context = this)

    private val binding by lazy {
        ActivityListaDepartamentoActivityBinding.inflate(layoutInflater)
    }
    private val departamentoDao by lazy {
        AppDatabase.instancia(this).departamentoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vaiParaFunc()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        val dbDep = AppDatabase.instancia(this)
        val departamentoDao = dbDep.departamentoDao()
        adapter.atualiza(departamentoDao.buscaTodosDep())
    }

    private fun configuraFab() {
        val fab = binding.activityListaDepartamentosFab
        fab.setOnClickListener {
            vaiParaFormularioDepartamento()
        }
    }

     private fun vaiParaFormularioDepartamento() {
        val intent = Intent(this, FormularioDepartamentoActivity::class.java)
        startActivity(intent)
    }

    private fun vaiParaFunc() {
        val recyclerView = binding.activityListaDepartamentosRecyclerView
        recyclerView.adapter = adapter
        adapter.vaiParaFunc = { departamento ->
            val intent = Intent(
                this,
                ListaFuncionariosActivity::class.java
            ).apply {
                putExtra(CHAVE_DEPARTAMENTO_ID, departamento.id.toString())
                putExtra(CHAVE_DEPARTAMENTO_NOME, departamento.nome)
                Log.i("Usuários", "Nome Departamento: ${departamento.nome}")
            }
            startActivity(intent)
        }
    }

}