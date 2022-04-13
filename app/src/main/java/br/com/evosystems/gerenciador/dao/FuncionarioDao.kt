package br.com.evosystems.gerenciador.dao

import br.com.evosystems.gerenciador.model.Funcionario

class FuncionarioDao {

    fun adiciona(funcionario: Funcionario) {
        Companion.funcionario.add(funcionario)
    }

    fun buscaTodos(): List<Funcionario> {
        return funcionario.toList()
    }

    companion object {
        private val funcionario = mutableListOf<Funcionario>()
    }

}