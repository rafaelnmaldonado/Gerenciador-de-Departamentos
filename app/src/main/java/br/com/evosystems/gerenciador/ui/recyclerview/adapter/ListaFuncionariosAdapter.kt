package br.com.evosystems.gerenciador.ui.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.evosystems.gerenciador.databinding.FuncionarioItemBinding
import br.com.evosystems.gerenciador.extensions.tentaCarregarImagem
import br.com.evosystems.gerenciador.model.Departamento
import br.com.evosystems.gerenciador.model.Funcionario
import kotlinx.coroutines.flow.Flow

class ListaFuncionariosAdapter(
    private val context: Context,
    var quandoClicaNoFuncionario: (funcionario: Funcionario) -> Unit = {},
    funcionarios: List<Funcionario> = emptyList()
) : RecyclerView.Adapter<ListaFuncionariosAdapter.ViewHolder>() {

    private val funcionarios = funcionarios.toMutableList()

    inner class ViewHolder(private val binding: FuncionarioItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var funcionario: Funcionario

        init {
            itemView.setOnClickListener {
                if (::funcionario.isInitialized) {
                    quandoClicaNoFuncionario(funcionario)
                }
            }
        }

        fun vincula(funcionario: Funcionario) {
            this.funcionario = funcionario
            val nome = binding.funcionarioItemNome
            nome.text = funcionario.nome
            val rg = binding.funcionarioItemRg
            rg.text = funcionario.rg

            if (funcionario.foto == null) {
                funcionario.foto = "https://veterans.utah.gov/wp-content/uploads/shutterstock_1677509740.jpg"
            }

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
