package br.com.evosystems.gerenciador.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.com.evosystems.gerenciador.databinding.ActivityListaFuncionarioActivityBinding
import br.com.evosystems.gerenciador.dao.FuncionarioDao
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.ui.recyclerview.adapter.ListaFuncionariosAdapter

class ListaFuncionariosActivity : AppCompatActivity() {

    private val dao = FuncionarioDao()
    private val adapter = ListaFuncionariosAdapter(context = this, funcionarios = dao.buscaTodos())
    private val binding by lazy {
        ActivityListaFuncionarioActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()

        val dbFunc = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "funcionario.db"
        ).allowMainThreadQueries().build()
        val funcionarioDao = dbFunc.funcionarioDao()
        adapter.atualiza(funcionarioDao.buscaTodos())
    }

    override fun onResume() {
        super.onResume()
        val dbFunc = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "funcionario.db"
        ).allowMainThreadQueries().build()
        val funcionarioDao = dbFunc.funcionarioDao()
        adapter.atualiza(funcionarioDao.buscaTodos())
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
    }

}