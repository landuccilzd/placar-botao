package br.com.landucci.botaoapi.resource

import br.com.landucci.botaoapi.model.Jogo
import br.com.landucci.botaoapi.model.command.SinalizacaoEventoCommand
import br.com.landucci.botaoapi.service.impl.JogoServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/jogo")
class JogoResource @Autowired constructor(
        private val jogoService: JogoServiceImpl
) {
    @GetMapping
    fun listar(): List<Jogo> {
        return this.jogoService.listar()
    }

    @GetMapping("/{id}")
    fun buscar(@PathVariable("id") id: Long): Jogo {
        return this.jogoService.buscar(id)
    }

    @PostMapping
    fun inserir(@RequestBody jogo: Jogo): Jogo {
        return this.jogoService.inserir(jogo)
    }

    @PutMapping
    fun alterar(@RequestBody jogo: Jogo): Jogo {
        return this.jogoService.alterar(jogo)
    }

    @DeleteMapping("/{id}")
    fun excluir(@PathVariable("id") id: Long) {
        this.jogoService.excluir(id)
    }

    @PutMapping("/evento")
    fun sinalizarEvento(@RequestBody command: SinalizacaoEventoCommand): Jogo {
        return this.jogoService.sinalizarEvento(command.jogo, command.jogador, command.tipoEvento)
    }

    @PutMapping("/finalizar/{id}")
    fun finalizar(@PathVariable("id") id: Long): Jogo {
        return this.jogoService.finalizar(id)
    }
}