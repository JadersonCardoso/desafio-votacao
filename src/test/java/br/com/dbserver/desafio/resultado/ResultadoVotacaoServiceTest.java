package br.com.dbserver.desafio.resultado;

import br.com.dbserver.desafio.mapper.MockSessao;
import br.com.dbserver.desafio.sessao.SessaoModel;
import br.com.dbserver.desafio.sessao.SessaoRepository;
import br.com.dbserver.desafio.voto.VotoRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class ResultadoVotacaoServiceTest {

    @Mock
    private VotoRepository votoRepository;
    @Mock
    private SessaoRepository sessaoRepository;

    private ResultadoVotacaoService resultadoVotacaoService;

    private SessaoModel sessaoModel;

    @BeforeEach
    void setup() {
        this.resultadoVotacaoService = new ResultadoVotacaoService(votoRepository, sessaoRepository);
        MockSessao input = new MockSessao();
        this.sessaoModel = input.mockEntity();
    }
    @Test
    @Order(1)
    @DisplayName("Deve Retorna o resultado da votação.")
    void retornaResultadoTest() {

        SessaoModel sessaoFechada = new SessaoModel();
        sessaoFechada.setId(this.sessaoModel.getId());
        sessaoFechada.setPauta(this.sessaoModel.getPauta());
        sessaoFechada.setInicio(Instant.now().minus(Duration.ofMinutes(10)));
        sessaoFechada.setFim(Instant.now().minus(Duration.ofMinutes(5)));

        when(this.sessaoRepository.findByPautaId(this.sessaoModel.getPauta().getId())).thenReturn(Optional.of(sessaoFechada));

        var resultado = this.resultadoVotacaoService.retornaResultado(this.sessaoModel.getPauta().getId());

        Assertions.assertNotNull(resultado.resultado());

    }
}