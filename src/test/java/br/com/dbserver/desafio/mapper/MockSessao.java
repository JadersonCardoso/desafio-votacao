package br.com.dbserver.desafio.mapper;

import br.com.dbserver.desafio.pauta.PautaDTO;
import br.com.dbserver.desafio.pauta.PautaModel;
import br.com.dbserver.desafio.sessao.SessaoDTO;
import br.com.dbserver.desafio.sessao.SessaoModel;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MockSessao {
    public SessaoDTO mockDTO() {
        PautaDTO pautaDTO = new PautaDTO(UUID.fromString("1e83546-a98a-44b5-a3eb-ce7c80309c05"),"Pauta Teste");
        return new SessaoDTO(UUID.randomUUID(),
                Instant.now(), Instant.now().plus(Duration.ofSeconds(60))
                ,pautaDTO);
    }
    public SessaoModel mockEntity() {
        Random random = new Random();
        PautaModel pautaModel = new PautaModel();
        pautaModel.setId(UUID.fromString("1e83546-a98a-44b5-a3eb-ce7c80309c05"));
        pautaModel.setTitulo("Pauta Teste " + random.nextInt(15));
        SessaoModel sessaoModel = new SessaoModel();
        sessaoModel.setId(UUID.randomUUID());
        sessaoModel.setInicio(Instant.now());
        sessaoModel.setFim(Instant.now().plus(Duration.ofSeconds(60)));
        sessaoModel.setPauta(pautaModel);
        return sessaoModel;
    }

    public List<SessaoModel> mockEntityList() {
        List<SessaoModel> sessoes = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            sessoes.add(mockEntity());
        }
        return sessoes;
    }


}
