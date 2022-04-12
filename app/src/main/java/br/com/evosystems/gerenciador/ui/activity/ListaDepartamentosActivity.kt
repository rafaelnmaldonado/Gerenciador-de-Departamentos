package br.com.evosystems.gerenciador.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.evosystems.departamento.databinding.ActivityListaDepartamentoActivityBinding
import br.com.evosystems.gerenciador.dao.DepartamentoDao
import br.com.evosystems.gerenciador.ui.recyclerview.adapter.ListaDepartamentosAdapter

class ListaDepartamentosActivity : AppCompatActivity() {

    private val dao = DepartamentoDao()
    private val adapter = ListaDepartamentosAdapter(context = this, departamentos = dao.buscaTodos())
    private val binding by lazy {
        ActivityListaDepartamentoActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
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

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaDepartamentosRecyclerView
        recyclerView.adapter = adapter
    }

}