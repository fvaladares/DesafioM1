package br.com.xpeducacao.desafiomdulo1.model.enum

enum class UnitEnum {
    KG,
    LITROS,
    UNIDADE,
    UNDEFINED;

    companion object {
        fun valueOf(value: Int): UnitEnum {
            return when (value) {
                0 -> KG
                1 -> LITROS
                2 -> UNIDADE
                else -> UNDEFINED
            }
        }
    }
}