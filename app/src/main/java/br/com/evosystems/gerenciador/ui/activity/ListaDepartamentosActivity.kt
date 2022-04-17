package br.com.evosystems.gerenciador.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.evosystems.gerenciador.databinding.ActivityListaDepartamentoActivityBinding
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.ui.recyclerview.adapter.ListaDepartamentosAdapter

class ListaDepartamentosActivity : AppCompatActivity() {

    private val adapter = ListaDepartamentosAdapter(context = this)
    private val binding by lazy {
        ActivityListaDepartamentoActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vaiParaFuncionarios()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        val dbDep = AppDatabase.instanciaDep(this)
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

    private fun vaiParaFuncionarios() {
        val recyclerView = binding.activityListaDepartamentosRecyclerView
        recyclerView.adapter = adapter
        adapter.vaiParaFunc = {
            val intent = Intent(
                this,
                ListaFuncionariosActivity::class.java
            ).apply {
                //putExtra(CHAVE_DEPARTAMENTO, it)
            }
            startActivity(intent)
        }
    }

}