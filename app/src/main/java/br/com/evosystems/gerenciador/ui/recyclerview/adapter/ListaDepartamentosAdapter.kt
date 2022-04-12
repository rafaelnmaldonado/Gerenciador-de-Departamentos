package br.com.evosystems.gerenciador.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.evosystems.departamento.databinding.DepartamentoItemBinding
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.extensions.tentaCarregarImagem

class ListaDepartamentosAdapter(
    private val context: Context,
    departamentos: List<Departamento>
) : RecyclerView.Adapter<ListaDepartamentosAdapter.ViewHolder>() {

    private val departamentos = departamentos.toMutableList()

    class ViewHolder(private val binding: DepartamentoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(departamento: Departamento) {
            val id = binding.departamentoItemId
            id.text = departamento.id.toString()
            val nome = binding.departamentoItemNome
            nome.text = departamento.nome
            val descricao = binding.departamentoItemSigla
            descricao.text = departamento.sigla

            val visibilidade = if (departamento.imagem != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = visibilidade

            binding.imageView.tentaCarregarImagem(departamento.imagem)
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
