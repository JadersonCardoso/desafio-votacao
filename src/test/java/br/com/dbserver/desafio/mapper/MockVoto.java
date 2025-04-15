package br.com.dbserver.desafio.mapper;

import br.com.dbserver.desafio.voto.VotoModel;
import br.com.dbserver.desafio.voto.VotoRequestDTO;
import br.com.dbserver.desafio.voto.VotoValor;

import java.util.UUID;

public class MockVoto {

    public VotoRequestDTO mockRequest() {
        return new VotoRequestDTO(UUID.fromString("4a390eeb-2512-4813-b55a-4b57fa21efdc"),
                UUID.fromString("0594e80e-bd42-439f-8a50-482cfee1f696"),
                "SIM");
    }

    public VotoModel mockEntity() {
        MockPauta pauta = new MockPauta();
        VotoModel voto = new VotoModel();
        voto.setPauta(pauta.mockEntity());
        voto.setAssociadoId(UUID.fromString("0594e80e-bd42-439f-8a50-482cfee1f696"));
        voto.setValor(VotoValor.SIM);
        return voto;
    }

}
