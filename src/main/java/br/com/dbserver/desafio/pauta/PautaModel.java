package br.com.dbserver.desafio.pauta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "TB_PAUTA")
public class PautaModel {

    @Id
    @Column(name = "pauta_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String titulo;
}
