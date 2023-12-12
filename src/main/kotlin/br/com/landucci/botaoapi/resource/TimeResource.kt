package br.com.landucci.botaoapi.resource

import br.com.landucci.botaoapi.model.Time
import br.com.landucci.botaoapi.model.command.EscalcaoCommand
import br.com.landucci.botaoapi.service.impl.TimeServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/time")
class TimeResource @Autowired constructor(
        private val timeService: TimeServiceImpl
) {
    @GetMapping
    fun listar(): List<Time> {
        return this.timeService.listar()
    }

    @GetMapping("/{id}")
    fun buscar(@PathVariable("id") id: Long): Time {
        return this.timeService.buscar(id)
    }

    @PostMapping
    fun inserir(@RequestBody time: Time): Time {
        return this.timeService.inserir(time)
    }

    @PutMapping()
    fun alterar(@RequestBody time: Time): Time {
        return this.timeService.alterar(time)
    }

    @DeleteMapping("/{id}")
    fun excluir(@PathVariable("id") id: Long) {
        this.timeService.excluir(id)
    }

    @PutMapping("/escalar")
    fun escalar(@RequestBody command: EscalcaoCommand): Time {
        return this.timeService.escalarJogador(command.time, command.jogador)
    }
}