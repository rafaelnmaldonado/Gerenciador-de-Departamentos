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
        private val funcionario = mutableListOf(
            Funcionario(
                id = 1,
                nome = "Rafael",
                _rg = 39783642,
                digitoRg = "X",
                foto = "https://buriticupu.ifma.edu.br/wp-content/uploads/sites/16/2019/08/pngtree-user-icon-vector-illustration-in-glyph-style-for-any-purpose-png-image_975597.jpg"
            )
        )
    }

}