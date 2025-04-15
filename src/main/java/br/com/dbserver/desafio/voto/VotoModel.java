package br.com.dbserver.desafio.voto;

import br.com.dbserver.desafio.pauta.PautaModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TB_VOTO")
public class VotoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "voto_id")
    private UUID id;

    @Column(name = "associado_id",nullable = false)
    private UUID associadoId;

    @Column(name = "voto_valor", nullable = false)
    @Enumerated(EnumType.STRING)
    private VotoValor valor;

    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private PautaModel pauta;


}
