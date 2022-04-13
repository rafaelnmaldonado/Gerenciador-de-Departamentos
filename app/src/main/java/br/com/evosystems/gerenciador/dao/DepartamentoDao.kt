package br.com.evosystems.gerenciador.dao

import br.com.evosystems.gerenciador.model.Departamento

class DepartamentoDao {

    fun adiciona(departamento: Departamento) {
        Companion.departamento.add(departamento)
    }

    fun buscaTodos(): List<Departamento> {
        return departamento.toList()
    }

    companion object {
        private val departamento = mutableListOf<Departamento>()
    }

}