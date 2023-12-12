package br.com.landucci.botaoapi.exception

class BotaoNotFoundException(private val mensagem: String) : RuntimeException(mensagem)