package br.com.dbserver.desafio.sessao;

import br.com.dbserver.desafio.pauta.PautaModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TB_SESSAO")
public class SessaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sessao_id")
    private UUID id;
    private Instant inicio;
    private Instant fim;
    @OneToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private PautaModel pauta;
    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;

    @PrePersist
    public void prePersist() {
        this.dataAbertura = LocalDateTime.now();
    }


}
