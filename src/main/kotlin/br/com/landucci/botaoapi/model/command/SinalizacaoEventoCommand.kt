package br.com.landucci.botaoapi.model.command

import br.com.landucci.botaoapi.model.Jogador
import br.com.landucci.botaoapi.model.Jogo
import br.com.landucci.botaoapi.model.enums.TipoEvento

class SinalizacaoEventoCommand(
        val jogo: Jogo,
        val jogador: Jogador,
        val tipoEvento: TipoEvento
)