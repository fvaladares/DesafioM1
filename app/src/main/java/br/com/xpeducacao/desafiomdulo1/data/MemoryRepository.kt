package br.com.xpeducacao.desafiomdulo1.data

import br.com.xpeducacao.desafiomdulo1.model.entities.Produto

class MemoryRepository(newList: MutableList<Produto>) {
    private val listDb: MutableList<Produto> = newList

    fun save(produto: Produto) {
        listDb.add(produto)
    }

    fun clearList() {
        listDb.clear()
    }

    fun getList() = listDb.toList()

    fun update(produto: Produto) {
        listDb.filter {
            it.nome == produto.nome && it.quantidade == produto.quantidade
        }.first().estaSelecionado = produto.estaSelecionado
    }
}