package br.com.landucci.botaoapi.model.repository

import br.com.landucci.botaoapi.model.Evento
import org.springframework.data.repository.CrudRepository

interface EventoRepository : CrudRepository<Evento, Long> {
}