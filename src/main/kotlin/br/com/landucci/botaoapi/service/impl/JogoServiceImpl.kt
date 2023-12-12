package br.com.landucci.botaoapi.service.impl

import br.com.landucci.botaoapi.exception.BotaoNotFoundException
import br.com.landucci.botaoapi.model.Evento
import br.com.landucci.botaoapi.model.Jogador
import br.com.landucci.botaoapi.model.Jogo
import br.com.landucci.botaoapi.model.enums.TipoEvento
import br.com.landucci.botaoapi.model.repository.JogoRepository
import br.com.landucci.botaoapi.service.JogoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class JogoServiceImpl @Autowired constructor(
        private val jogoRepository: JogoRepository,
        private val jogadorService: JogadorServiceImpl,
        private val eventoService: EventoServiceImpl
) : JogoService {

    override fun listar(): List<Jogo> {
        return this.jogoRepository.findAll().toList()
    }

    override fun buscar(id: Long): Jogo {
        val oJogo = this.jogoRepository.findById(id)
        if (!oJogo.isPresent) {
            throw BotaoNotFoundException("O jogo com ID $id não foi encontrado")
        }

        return oJogo.get()
    }

    override fun inserir(jogo: Jogo): Jogo {
        return this.jogoRepository.save(jogo)
    }

    override fun alterar(jogo: Jogo): Jogo {
        return this.jogoRepository.save(jogo)
    }

    override fun excluir(id: Long) {
        excluir(buscar(id))
    }

    override fun excluir(jogo: Jogo) {
        this.jogoRepository.delete(jogo)
    }

    override fun sinalizarEvento(jogo: Jogo, jogador: Jogador, tipoEvento: TipoEvento): Jogo {
        val game = buscar(jogo.id)
        val player = this.jogadorService.buscar(jogador.id)
        val event = Evento(player, game, tipoEvento, getMinutos(game))

        if (game.finalizado == 1) {
            throw RuntimeException("O jogo informado já está finalizado")
        }

        game.eventos.add(this.eventoService.inserir(event))
        contabilizaPontos(game, player, tipoEvento)

        if (tipoEvento == TipoEvento.FALTA || tipoEvento == TipoEvento.CARTAO_AMARELO) {
            contabilizaPenalidades(game, player)
        }

        return alterar(game)
    }

    private fun contabilizaPenalidades(jogo: Jogo, jogador: Jogador) {
        val totalFaltas = getTotalEventosJogador(jogo, jogador, TipoEvento.FALTA)
        if (totalFaltas == 3 || totalFaltas == 6) {
            val amarelo = Evento(jogador, jogo, TipoEvento.CARTAO_AMARELO, getMinutos(jogo))
            jogo.eventos.add(this.eventoService.inserir(amarelo))
        }

        val totalAmarelo = getTotalEventosJogador(jogo, jogador, TipoEvento.CARTAO_AMARELO)
        if (totalAmarelo == 2) {
            val vermelho = Evento(jogador, jogo, TipoEvento.CARTAO_VERMELHO, getMinutos(jogo))
            jogo.eventos.add(this.eventoService.inserir(vermelho))
        }
    }

    private fun contabilizaPontos(jogo: Jogo, jogador: Jogador, tipoEvento: TipoEvento) {
        if (TipoEvento.GOL_PRO == tipoEvento && jogo.timeCasa == jogador.time
                || TipoEvento.GOL_CONTRA == tipoEvento && jogo.timeVisitante == jogador.time
        ) {
            jogo.pontosCasa++
        }

        if (TipoEvento.GOL_PRO == tipoEvento && jogo.timeVisitante == jogador.time
                || TipoEvento.GOL_CONTRA == tipoEvento && jogo.timeCasa == jogador.time
        ) {
            jogo.pontosVisitante++
        }
    }

    override fun finalizar(id: Long): Jogo {
        val jogo = buscar(id)
        if (jogo.finalizado == 1) {
            throw RuntimeException("O jogo informado já está finalizado")
        }

        if (jogo.pontosCasa > jogo.pontosVisitante) {
            jogo.timeVencedor = jogo.timeCasa
        }
        if (jogo.pontosCasa < jogo.pontosVisitante) {
            jogo.timeVencedor = jogo.timeVisitante
        }

        jogo.finalizado = 1
        return alterar(jogo)
    }

    private fun getTotalEventosJogador(jogo: Jogo, jogador: Jogador, tipoEvento: TipoEvento): Int {
        var total = 0
        for (e in jogo.eventos) {
            if (tipoEvento == e.tipoEvento && e.jogador == jogador) {
                total++
            }
        }
        return total
    }

    private fun getMinutos(jogo: Jogo): Int {
        val data = LocalDateTime.now()
        return ((data.minute - jogo.data.minute) / 1000 / 60).toInt()
    }
}