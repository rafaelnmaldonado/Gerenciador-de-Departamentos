package br.com.evosystems.gerenciador.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.evosystems.gerenciador.databinding.ActivityListaDepartamentoActivityBinding
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.databinding.ActivityFormularioDepartamentoBinding
import br.com.evosystems.gerenciador.databinding.DepartamentoItemBinding
import br.com.evosystems.gerenciador.extensions.Toast
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.model.Funcionario
import br.com.evosystems.gerenciador.ui.recyclerview.adapter.ListaDepartamentosAdapter
import java.util.Collections.addAll

class ListaDepartamentosActivity : AppCompatActivity() {

    private val adapter = ListaDepartamentosAdapter(context = this)

    private val binding by lazy {
        ActivityListaDepartamentoActivityBinding.inflate(layoutInflater)
    }

    private val departamentoDao by lazy {
        AppDatabase.instancia(this).departamentoDao()
    }

    private val funcionarioDao by lazy {
        AppDatabase.instancia(this).funcionarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        editaDep()
        excluiDep()
        vaiParaFunc()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza()
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

    private fun editaDep() {
        val recyclerView = binding.activityListaDepartamentosRecyclerView
        recyclerView.adapter = adapter
        adapter.editaDep = { departamento ->
            val intent = Intent(
                this,
                FormularioDepartamentoActivity::class.java
            ).apply {
                putExtra(CHAVE_DEPARTAMENTO, departamento)
            }
            startActivity(intent)
        }
    }

    private fun excluiDep() {
        adapter.excluiDep = { departamento ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Excluir ${departamento.nome}?")
            builder.setMessage("Você deseja mesmo excluir o departamento ${departamento.nome} e todos seus funcionários?")
            builder.setPositiveButton("Sim"){_,_ ->
                Toast("${departamento.nome} excluído")
                Log.i("Excluir", "Excluir")
                funcionarioDao.deletaFuncDep(departamento.id)
                departamentoDao.deletaDep(departamento)
                adapter.atualiza()
            }
            builder.setNegativeButton("Não"){_,_ ->
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}