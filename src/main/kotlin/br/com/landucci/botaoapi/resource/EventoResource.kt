package br.com.landucci.botaoapi.resource

import br.com.landucci.botaoapi.model.Evento
import br.com.landucci.botaoapi.service.impl.EventoServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/evento")
class EventoResource @Autowired constructor(
        private val eventoService: EventoServiceImpl
) {
    @GetMapping
    fun listar(): List<Evento> {
        return this.eventoService.listar()
    }

    @GetMapping("/{id}")
    fun buscar(@PathVariable("id") id: Long): Evento {
        return this.eventoService.buscar(id)
    }

    @PostMapping
    fun inserir(@RequestBody evento: Evento): Evento {
        return this.eventoService.inserir(evento)
    }

    @PutMapping()
    fun alterar(@RequestBody evento: Evento): Evento {
        return this.eventoService.alterar(evento)
    }

    @DeleteMapping("/{id}")
    fun excluir(@PathVariable("id") id: Long) {
        this.eventoService.excluir(id)
    }
}