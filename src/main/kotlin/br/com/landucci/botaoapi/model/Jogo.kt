package br.com.landucci.botaoapi.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "jogo")
data class Jogo(
    @ManyToOne
    @JoinColumn(name = "time_casa_id")
    var timeCasa: Time,

    @ManyToOne
    @JoinColumn(name = "time_visitante_id")
    var timeVisitante: Time,

    @Temporal(TemporalType.TIMESTAMP)
    val data: LocalDateTime = LocalDateTime.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0L

    @ManyToOne
    @JoinColumn(name = "time_vencedor_id")
    var timeVencedor: Time? = null
    var pontosCasa = 0
    var pontosVisitante = 0
    var finalizado = 0

    @OneToMany(mappedBy = "jogo")
    var eventos = mutableListOf<Evento>()
        private set
}