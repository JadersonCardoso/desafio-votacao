package br.com.dbserver.desafio.sessao;

import br.com.dbserver.desafio.pauta.PautaModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TB_SESSAP")
public class SessaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sessao_id")
    private UUID id;
    private Instant inicio;
    private Instant fim;
    @OneToOne
    private PautaModel pauta;


}
