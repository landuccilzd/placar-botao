package br.com.landucci.botaoapi.service

import br.com.landucci.botaoapi.model.Jogador

interface JogadorService {
    fun listar(): List<Jogador>
    fun buscar(id: Long): Jogador
    fun inserir(jogador: Jogador): Jogador
    fun alterar(jogador: Jogador): Jogador
    fun excluir(id: Long)
    fun excluir(jogador: Jogador)
}