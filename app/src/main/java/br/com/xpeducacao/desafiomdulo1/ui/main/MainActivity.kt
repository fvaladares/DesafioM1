package br.com.xpeducacao.desafiomdulo1.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.xpeducacao.desafiomdulo1.R
import br.com.xpeducacao.desafiomdulo1.databinding.ActivityMainBinding
import br.com.xpeducacao.desafiomdulo1.model.entities.Produto
import br.com.xpeducacao.desafiomdulo1.ui.cadastro.NewItemActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val retorno = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            activityResult.data?.let {
                if (it.hasExtra(RETURN)) {
                    Log.i("IGTIinfo:", "Retorno: ${it.getParcelableExtra<Produto>(RETURN)}")
                    viewModel.saveItem(it.getParcelableExtra(RETURN))
                }
            }
        } else {
            Log.e("XPE::error", "Houve uma falha ao tentar obter os dados da activity.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareListeners()
        prepareObservers()
        initData()
    }

    private fun prepareListeners() {
        fabListener()
    }

    private fun fabListener() {
        binding.fabAdd.setOnClickListener {
            startNewItemActivity()
        }
    }

    private fun startNewItemActivity() {
        Intent(this, NewItemActivity::class.java).let {
            retorno.launch(it)
        }
    }

    private fun initData() {
        viewModel.initData()
    }

    private fun prepareObservers() {
        productListObserver()
    }

    private fun productListObserver() {
        viewModel.listaProdutos.observe(this) {
            prepareRecyclerView(it)
        }
    }

    private fun prepareRecyclerView(productList: List<Produto>?) {
        if (productList.isNullOrEmpty()) {
            binding.tvQuantidadeItens.text =
                resources.getQuantityString(R.plurals.amount_plural, 0, 0)
        } else {
            binding.apply {
                rvLista.layoutManager = LinearLayoutManager(applicationContext)
                rvLista.adapter = ProdutosAdapter(productList, applicationContext, ::updateItem)
                tvQuantidadeItens.text =
                    resources.getQuantityString(
                        R.plurals.amount_plural,
                        productList.size,
                        productList.size
                    )
            }
        }
    }

    fun updateItem(product: Produto) {
        viewModel.updateItem(product)
    }

    companion object {
        const val RETURN = "resposta_cadastro"
    }
}