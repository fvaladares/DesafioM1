package br.com.xpeducacao.desafiomdulo1.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.xpeducacao.desafiomdulo1.R
import br.com.xpeducacao.desafiomdulo1.databinding.ListItemProdutoBinding
import br.com.xpeducacao.desafiomdulo1.model.entities.Produto

class ProdutosAdapter(
    val items: List<Produto>,
    val applicationContext: Context,
    val updateItem: (produto: Produto) -> Unit
) : RecyclerView.Adapter<ProdutosAdapter.ProdutoViewHolder>() {

    private lateinit var binding: ListItemProdutoBinding

    inner class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(produto: Produto) {
            binding.tvNomeProduto.text = produto.nome
            binding.tvQuantidadeItens.text = applicationContext.getString(
                R.string.lbl_unidade,
                produto.quantidade.toString(),
                produto.unidade
            )
            binding.cbEstaSelecionado.isChecked = produto.estaSelecionado

            binding.cbEstaSelecionado.setOnClickListener {
                acaoCheckBox(produto)
            }
        }

        private fun acaoCheckBox(
            produto: Produto
        ) {
            produto.estaSelecionado = !produto.estaSelecionado
            binding.cbEstaSelecionado.isChecked = produto.estaSelecionado
            updateItem(produto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        binding = ListItemProdutoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProdutoViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}