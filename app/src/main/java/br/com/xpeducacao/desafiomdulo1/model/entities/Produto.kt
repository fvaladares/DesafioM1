package br.com.xpeducacao.desafiomdulo1.model.entities

import android.os.Parcelable
import br.com.xpeducacao.desafiomdulo1.model.enum.UnitEnum
import kotlinx.parcelize.Parcelize

@Parcelize
data class Produto(
    val nome: String,
    var estaSelecionado: Boolean = false,
    val quantidade: Double,
    val unidade: UnitEnum
) : Parcelable
