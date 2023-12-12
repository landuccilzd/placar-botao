package br.com.landucci.botaoapi.service.impl

import br.com.landucci.botaoapi.exception.BotaoNotFoundException
import br.com.landucci.botaoapi.model.Jogador
import br.com.landucci.botaoapi.model.Time
import br.com.landucci.botaoapi.model.enums.Posicao
import br.com.landucci.botaoapi.model.repository.TimeRepository
import br.com.landucci.botaoapi.service.TimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TimeServiceImpl @Autowired constructor(
        private val timeRepository: TimeRepository,
        private val jogadorServiceImpl: JogadorServiceImpl
) : TimeService {

    override fun listar(): List<Time> {
        return this.timeRepository.findAll().toList()
    }

    override fun buscar(id: Long): Time {
        val oTime = this.timeRepository.findById(id)
        if (!oTime.isPresent) {
            throw BotaoNotFoundException("O Time com ID $id não foi encontrado.")
        }
        return oTime.get()
    }

    override fun inserir(time: Time): Time {
        return this.timeRepository.save(time)
    }

    override fun alterar(time: Time): Time {
        return this.timeRepository.save(time)
    }

    override fun excluir(id: Long) {
        excluir(buscar(id))
    }

    override fun excluir(time: Time) {
        this.timeRepository.delete(time)
    }

    override fun escalarJogador(time: Time, jogador: Jogador): Time {
        val player = jogadorServiceImpl.buscar(jogador.id)
        if (player.time != null) {
            throw RuntimeException("O jogador ja está escalado no time ${player.time?.nome ?: "Time Genérico"}")
        }

        val team = buscar(time.id)
        if (team.jogadores.size >= 11) {
            throw RuntimeException("O time já está completo")
        }

        if (team.jogadores.contains(player)) {
            throw RuntimeException("O jogador já está no time")
        }

        require(player.numeroCamisa > 0) { "O jogador não pode ter número de camisa menor ou igual a zero" }

        if (player.posicao == Posicao.GOLEIRO) {
            for (j in team.jogadores) {
                if (j.posicao == Posicao.GOLEIRO) {
                    throw RuntimeException("Já existe um goleiro no time")
                }
            }
        }

        team.jogadores.add(player)
        player.time = team
        return alterar(team)
    }
}
