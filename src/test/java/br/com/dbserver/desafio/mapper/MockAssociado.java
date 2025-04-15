package br.com.dbserver.desafio.mapper;

import br.com.dbserver.desafio.associado.AssociadoDTO;
import br.com.dbserver.desafio.associado.AssociadoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockAssociado {

    public List<AssociadoModel> mockEntityList() {
        List<AssociadoModel> associados = new ArrayList<>();
        for (int i = 0; i < 14; i++ ) {
            associados.add(mockEntity());
        }
        return associados;
    }

    public AssociadoModel mockEntity() {
        AssociadoModel model = new AssociadoModel();
        model.setId(UUID.fromString("0594e80e-bd42-439f-8a50-482cfee1f696"));
        model.setCpf("10584910088");
        return model;
    }
    public AssociadoDTO mockDTO() {
        return new AssociadoDTO(UUID.fromString("0594e80e-bd42-439f-8a50-482cfee1f696"),"10584910088");
    }
}