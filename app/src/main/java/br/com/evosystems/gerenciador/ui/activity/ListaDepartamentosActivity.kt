package br.com.evosystems.gerenciador.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.evosystems.gerenciador.databinding.ActivityListaDepartamentoActivityBinding
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.databinding.ActivityFormularioDepartamentoBinding
import br.com.evosystems.gerenciador.databinding.DepartamentoItemBinding
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.model.Funcionario
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

    private fun editar(funcionario: Funcionario) {
        //val botaoSalvar = binding.departamentoItemEditar
        //val dbFunc = AppDatabase.instancia(this)
        //val funcionarioDao = dbFunc.funcionarioDao()
        //botaoSalvar.setOnClickListener {
            //val funcEditado = salvaEdicaoFunc(funcionario)
            //funcionarioDao.atualizaFunc(funcEditado)
            val tipoEdicao = "atualizado"
            //voltaListaFunc(funcEditado, tipoEdicao)
            finish()
        //}
    }

    private fun excluir(departamento: Departamento) {
        //val botaoExcluir = binding.departamentoItemExcluir
        //val dbFunc = AppDatabase.instancia(this)
        //val funcionarioDao = dbFunc.funcionarioDao()
        //botaoExcluir.setOnClickListener {
            val tipoEdicao = "excluído"
            //funcionarioDao.deletaFunc(funcionario)
            //voltaListaFunc(funcionario, tipoEdicao)
            finish()
        //}
    }

    override fun onResume() {
        super.onResume()
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