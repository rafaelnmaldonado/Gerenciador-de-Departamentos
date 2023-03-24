package br.com.evosystems.gerenciador.ui.recyclerview.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import br.com.evosystems.gerenciador.database.AppDatabase
import br.com.evosystems.gerenciador.databinding.DepartamentoItemBinding
import br.com.evosystems.gerenciador.model.Departamento

class ListaDepartamentosAdapter(
    private val context: Context,
    var vaiParaFunc: (departamento: Departamento) -> Unit = {},
    var editaDep: (departamento: Departamento) -> Unit = {},
    var excluiDep: (departamento: Departamento) -> Unit = {},
    departamentos: List<Departamento> = emptyList()
) : RecyclerView.Adapter<ListaDepartamentosAdapter.ViewHolder>() {

    private val departamentos = departamentos.toMutableList()

    private val departamentoDao by lazy {
        AppDatabase.instancia(context).departamentoDao()
    }

    inner class ViewHolder(private val binding: DepartamentoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var departamento: Departamento

        init {
            val botaoSalvar = binding.departamentoItemEditar
            botaoSalvar.setOnClickListener {
                if (::departamento.isInitialized) {
                    Log.i("InfoDepartamento","$departamento")
                    editaDep(departamento)
                }
            }
            val botaoExcluir = binding.departamentoItemExcluir
            botaoExcluir.setOnClickListener {
                if (::departamento.isInitialized) {
                    Log.i("InfoDepartamento","$departamento")
                    excluiDep(departamento)
                }
            }
            itemView.setOnClickListener {
                if (::departamento.isInitialized) {
                    vaiParaFunc(departamento)
                }
            }
        }

        fun vincula(departamento: Departamento) {
            this.departamento = departamento
            val nome = binding.departamentoItemNome
            nome.text = departamento.nome
            val sigla = binding.departamentoItemSigla
            sigla.text = departamento.sigla
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = DepartamentoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val departamento = departamentos[position]
        holder.vincula(departamento)
    }

    override fun getItemCount(): Int = departamentos.size

    fun atualiza() {
        this.departamentos.clear()
        val depAtualiza = departamentoDao.buscaTodosDep()
        this.departamentos.addAll(depAtualiza)
        notifyDataSetChanged()
    }

}
