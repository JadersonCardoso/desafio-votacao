package br.com.dbserver.desafio.mapper;

import br.com.dbserver.desafio.pauta.PautaDTO;
import br.com.dbserver.desafio.pauta.PautaModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockPauta {
    public PautaModel mockEntity () {
        PautaModel pauta = new PautaModel();
        pauta.setId(UUID.fromString("4a390eeb-2512-4813-b55a-4b57fa21efdc"));
        pauta.setTitulo("Pauta teste");
        return pauta;
    }
    public PautaDTO mockDTO() {
        return new PautaDTO(UUID.fromString("4a390eeb-2512-4813-b55a-4b57fa21efdc"), "Pauta Teste");
    }

    public PautaDTO mockDtoRequest() {
        return new PautaDTO(null, "Pauta Teste");
    }

    public List<PautaModel> mockEntityList() {
        List<PautaModel> pautas = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            pautas.add(mockEntity());
        }
        return pautas;
    }
}
