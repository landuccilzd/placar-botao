package br.com.landucci.botaoapi.resource

import br.com.landucci.botaoapi.model.Jogador
import br.com.landucci.botaoapi.service.impl.JogadorServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/jogador")
class JogadorResource @Autowired constructor(
        private val jogadorService: JogadorServiceImpl
) {
    @GetMapping
    fun listar(): List<Jogador> {
        return this.jogadorService.listar()
    }

    @GetMapping("/{id}")
    fun buscar(@PathVariable("id") id: Long): Jogador {
        return this.jogadorService.buscar(id)
    }

    @PostMapping
    fun inserir(@RequestBody jogador: Jogador): Jogador {
        return this.jogadorService.inserir(jogador)
    }

    @PutMapping()
    fun alterar(@RequestBody jogador: Jogador): Jogador {
        return this.jogadorService.alterar(jogador)
    }

    @DeleteMapping("/{id}")
    fun excluir(@PathVariable("id") id: Long) {
        this.jogadorService.excluir(id)
    }
}