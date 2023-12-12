package br.com.landucci.botaoapi.model.repository

import br.com.landucci.botaoapi.model.Time
import org.springframework.data.repository.CrudRepository

interface TimeRepository : CrudRepository<Time, Long> {
}