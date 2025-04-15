package br.com.dbserver.desafio.mapper;

import br.com.dbserver.desafio.voto.VotoModel;
import br.com.dbserver.desafio.voto.VotoRequestDTO;
import br.com.dbserver.desafio.voto.VotoValor;

import java.util.UUID;

public class MockVoto {

    public VotoRequestDTO mockRequest() {
        return new VotoRequestDTO(UUID.fromString("4a390eeb-2512-4813-b55a-4b57fa21efdc"),
                UUID.fromString("b25ddb9a-f9bc-4b5b-afa0-f97c6229d5c6"),
                "SIM");
    }

    public VotoModel mockEntity() {
        MockPauta pauta = new MockPauta();
        VotoModel voto = new VotoModel();
        voto.setPauta(pauta.mockEntity());
        voto.setAssociadoId(UUID.fromString("b25ddb9a-f9bc-4b5b-afa0-f97c6229d5c6"));
        voto.setValor(VotoValor.SIM);
        return voto;
    }

}
