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
        private val departamento = mutableListOf(
            Departamento(
                id = 1,
                nome = "Desenvolvimento",
                sigla = "DEV",
                imagem = "https://cdn-icons-png.flaticon.com/512/181/181548.png"
            )
        )
    }

}