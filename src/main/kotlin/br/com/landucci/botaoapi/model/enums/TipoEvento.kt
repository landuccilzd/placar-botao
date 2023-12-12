package br.com.landucci.botaoapi.model.enums;

enum class TipoEvento(val valor: String) {
    GOL_PRO("GP"),
    GOL_CONTRA("GC"),
    FALTA("FT"),
    CARTAO_AMARELO("CA"),
    CARTAO_VERMELHO("CV"),
    EXPULSAO("EX")
}