package br.com.landucci.botaoapi.model

import br.com.landucci.botaoapi.model.enums.TipoEvento
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "evento")
data class Evento(
    @ManyToOne
    @JoinColumn(name = "jogador_id")
    val jogador: Jogador,

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    @JsonBackReference
    val jogo: Jogo,

    val tipoEvento: TipoEvento,
    val tempo: Int
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id = 0L
}