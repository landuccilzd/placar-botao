package br.com.landucci.botaoapi.service

import br.com.landucci.botaoapi.model.Jogador
import br.com.landucci.botaoapi.model.Time

interface TimeService {
    fun listar(): List<Time>
    fun buscar(id: Long): Time
    fun inserir(time: Time): Time
    fun alterar(time: Time): Time
    fun excluir(id: Long)
    fun excluir(time: Time)
    fun escalarJogador(time: Time, jogador: Jogador): Time
}