package br.com.evosystems.gerenciador.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.evosystems.gerenciador.databinding.DepartamentoItemBinding
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.model.Funcionario

class ListaDepartamentosAdapter(
    private val context: Context,
    var vaiParaFunc: (departamento: Departamento) -> Unit = {},
    departamentos: List<Departamento> = emptyList()
) : RecyclerView.Adapter<ListaDepartamentosAdapter.ViewHolder>() {

    private val departamentos = departamentos.toMutableList()

    inner class ViewHolder(private val binding: DepartamentoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var departamento: Departamento

        init {
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

    fun atualiza(departamentos: List<Departamento>) {
        this.departamentos.clear()
        this.departamentos.addAll(departamentos)
        notifyDataSetChanged()
    }

}
