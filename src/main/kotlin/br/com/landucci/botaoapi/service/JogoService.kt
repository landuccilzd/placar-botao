package br.com.landucci.botaoapi.service

import br.com.landucci.botaoapi.model.Jogador
import br.com.landucci.botaoapi.model.Jogo
import br.com.landucci.botaoapi.model.enums.TipoEvento

interface JogoService {
    fun listar(): List<Jogo>
    fun buscar(id: Long): Jogo
    fun inserir(jogo: Jogo): Jogo
    fun alterar(jogo: Jogo): Jogo
    fun excluir(id: Long)
    fun excluir(jogo: Jogo)
    fun sinalizarEvento(jogo: Jogo, jogador: Jogador, tipoEvento: TipoEvento): Jogo
    fun finalizar(id: Long): Jogo
}