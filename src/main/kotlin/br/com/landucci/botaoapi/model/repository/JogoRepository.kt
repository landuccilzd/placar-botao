package br.com.landucci.botaoapi.model.repository

import br.com.landucci.botaoapi.model.Jogo
import org.springframework.data.repository.CrudRepository

interface JogoRepository : CrudRepository<Jogo, Long> {
}