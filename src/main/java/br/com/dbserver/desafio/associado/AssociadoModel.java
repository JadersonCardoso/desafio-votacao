package br.com.dbserver.desafio.associado;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "TB_ASSOCIADO")
public class AssociadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "associado_id")
    private UUID id;

    private String cpf;
}
