package br.com.landucci.botaoapi.model.repository

import br.com.landucci.botaoapi.model.Jogador
import org.springframework.data.repository.CrudRepository

interface JogadorRepository : CrudRepository<Jogador, Long> {
}