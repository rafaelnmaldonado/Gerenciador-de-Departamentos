package br.com.evosystems.gerenciador.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.evosystems.departamento.databinding.FuncionarioItemBinding
import br.com.evosystems.gerenciador.model.Funcionario
import br.com.evosystems.gerenciador.extensions.tentaCarregarImagem

class ListaFuncionariosAdapter(
    private val context: Context,
    funcionarios: List<Funcionario>
) : RecyclerView.Adapter<ListaFuncionariosAdapter.ViewHolder>() {

    private val funcionarios = funcionarios.toMutableList()

    class ViewHolder(private val binding: FuncionarioItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(funcionario: Funcionario) {
            val id = binding.funcionarioItemId
            id.text = funcionario.id.toString()
            val nome = binding.funcionarioItemNome
            nome.text = funcionario.nome
            val rg = binding.funcionarioItemRg
            rg.text = funcionario.rg

            val visibilidade = if (funcionario.foto != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = visibilidade

            binding.imageView.tentaCarregarImagem(funcionario.foto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = FuncionarioItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val funcionario = funcionarios[position]
        holder.vincula(funcionario)
    }

    override fun getItemCount(): Int = funcionarios.size

    fun atualiza(funcionarios: List<Funcionario>) {
        this.funcionarios.clear()
        this.funcionarios.addAll(funcionarios)
        notifyDataSetChanged()
    }

}
