package br.com.landucci.botaoapi.service.impl

import br.com.landucci.botaoapi.exception.BotaoNotFoundException
import br.com.landucci.botaoapi.model.Evento
import br.com.landucci.botaoapi.model.repository.EventoRepository
import br.com.landucci.botaoapi.service.EventoService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EventoServiceImpl @Autowired constructor(
        private val eventoRepository: EventoRepository
) : EventoService {

    override fun listar(): List<Evento> {
        return this.eventoRepository.findAll().toList()
    }

    override fun buscar(id: Long): Evento {
        val oEvento = this.eventoRepository.findById(id)
        if (!oEvento.isPresent) {
            throw BotaoNotFoundException("O evento com ID $id não foi encontrado")
        }

        return oEvento.get()
    }

    override fun inserir(evento: Evento): Evento {
        return this.eventoRepository.save(evento)
    }

    override fun alterar(evento: Evento): Evento {
        return this.eventoRepository.save(evento)
    }

    override fun excluir(id: Long) {
        excluir(buscar(id))
    }

    override fun excluir(evento: Evento) {
        this.eventoRepository.delete(evento)
    }
}