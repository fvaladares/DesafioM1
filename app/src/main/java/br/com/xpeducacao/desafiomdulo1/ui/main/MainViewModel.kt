package br.com.xpeducacao.desafiomdulo1.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.xpeducacao.desafiomdulo1.data.MemoryRepository
import br.com.xpeducacao.desafiomdulo1.model.entities.Produto

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _listaProdutos = MutableLiveData<List<Produto>>()
    val listaProdutos: MutableLiveData<List<Produto>> = _listaProdutos
    private val memoryRepository: MemoryRepository = MemoryRepository((mutableListOf()))

    fun initData() {
        _listaProdutos.value = memoryRepository.getList()
    }

    fun saveItem(produto: Produto?) {
        produto?.let {
            memoryRepository.save(it)
            updateProductsList()
        }
    }

    private fun updateProductsList() {
        _listaProdutos.value = memoryRepository.getList()
    }

    fun updateItem(product: Produto) {
        product.let {
            memoryRepository.update(it)
            updateProductsList()
        }
    }
}