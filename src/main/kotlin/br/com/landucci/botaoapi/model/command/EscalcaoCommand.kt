package br.com.landucci.botaoapi.model.command

import br.com.landucci.botaoapi.model.Jogador
import br.com.landucci.botaoapi.model.Time

class EscalcaoCommand(
        var time: Time,
        var jogador: Jogador
)