package br.com.dbserver.desafio.voto;

import br.com.dbserver.desafio.exception.BusinessException;
import br.com.dbserver.desafio.exception.FileNotFoundException;
import br.com.dbserver.desafio.mapper.MockSessao;
import br.com.dbserver.desafio.mapper.MockVoto;
import br.com.dbserver.desafio.pauta.PautaRepository;
import br.com.dbserver.desafio.sessao.SessaoModel;
import br.com.dbserver.desafio.sessao.SessaoRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    private MockVoto input;
    private MockSessao inputSessao;
    @Mock
    private VotoRepository votoRepository;
    @Mock
    private PautaRepository pautaRepository;
    @Mock
    private SessaoRepository sessaoRepository;

    private VotoService votoService;
    @BeforeEach
    void setup() {
        this.votoService = new VotoService(votoRepository, pautaRepository, sessaoRepository);
        this.input = new MockVoto();
        this.inputSessao = new MockSessao();
    }

    @Test
    @Order(1)
    @DisplayName("Deve salvar um voto com sucesso")
    void inserirVotoTest() {
        VotoModel voto = this.input.mockEntity();

        when(this.pautaRepository.findById(voto.getPauta().getId())).thenReturn(Optional.of(voto.getPauta()));
        when(this.votoRepository.existsByPautaIdAndAssociadoId(voto.getPauta().getId(), voto.getAssociadoId())).thenReturn(false);
        when(this.sessaoRepository.findByPautaId(voto.getPauta().getId())).thenReturn(Optional.of(this.inputSessao.mockEntity()));
        when(this.votoRepository.save(any(VotoModel.class))).thenReturn(voto);

        this.votoService.inserirVoto(this.input.mockRequest());

        verify(votoRepository, times(1)).save(any(VotoModel.class));

    }

    @Test
    @Order(2)
    @DisplayName("Deve gerar erro se não existe pauta cadastrada.")
    void inserirVotoPautNotFoundExceptionTest() {

        Executable chamada = () -> this.votoService.inserirVoto(this.input.mockRequest());
        Exception exception = assertThrows(FileNotFoundException.class, chamada);

        String mensagemEsperada = "Não foi encontrada pauta com o ID informado.";
        String mensagemAtual = exception.getMessage();

        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }
    @Test
    @Order(3)
    @DisplayName("Deve gerar erro caso já exista voto do associado")
    void inserirVotoPautaAssociadoBusinessExceptionTest() {
        VotoModel voto = this.input.mockEntity();

        when(this.pautaRepository.findById(voto.getPauta().getId())).thenReturn(Optional.of(voto.getPauta()));
        when(this.votoRepository.existsByPautaIdAndAssociadoId(voto.getPauta().getId(), voto.getAssociadoId())).thenReturn(true);

        Executable chamada = () -> this.votoService.inserirVoto(this.input.mockRequest());
        Exception exception = assertThrows(BusinessException.class, chamada);

        String mensagemEsperada = "Já existe voto do associado pata esta pauta.";
        String mensagemAtual = exception.getMessage();

        assertTrue(mensagemAtual.contains(mensagemEsperada));

    }

    @Test
    @Order(4)
    @DisplayName("Deve gerar erro não existe sessão aberta")
    void inserirVotoSessaoNotFountExceptionTest() {
        VotoModel voto = this.input.mockEntity();

        when(this.pautaRepository.findById(voto.getPauta().getId())).thenReturn(Optional.of(voto.getPauta()));
        when(this.votoRepository.existsByPautaIdAndAssociadoId(voto.getPauta().getId(), voto.getAssociadoId())).thenReturn(false);

        Executable chamada = () -> this.votoService.inserirVoto(this.input.mockRequest());
        Exception exception = assertThrows(FileNotFoundException.class, chamada);

        String mensagemEsperada = "Não foi encontrada sessão para esta pauta.";
        String mensagemAtual = exception.getMessage();

        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }
    @Test
    @Order(5)
    @DisplayName("Deve gerar erro caso a sessão esteja fechada")
    void inserirVotoSessaoFechadaTeste() {
        VotoModel voto = this.input.mockEntity();

        when(this.pautaRepository.findById(voto.getPauta().getId())).thenReturn(Optional.of(voto.getPauta()));
        when(this.votoRepository.existsByPautaIdAndAssociadoId(voto.getPauta().getId(), voto.getAssociadoId())).thenReturn(false);

        SessaoModel sessaoFechada = new SessaoModel();
        sessaoFechada.setId(UUID.randomUUID());
        sessaoFechada.setPauta(voto.getPauta());
        sessaoFechada.setInicio(Instant.now().minus(Duration.ofMinutes(10)));
        sessaoFechada.setFim(Instant.now().minus(Duration.ofMinutes(5)));

        when(this.sessaoRepository.findByPautaId(voto.getPauta().getId())).thenReturn(Optional.of(sessaoFechada));

        Executable chamada = () -> this.votoService.inserirVoto(this.input.mockRequest());
        Exception exception = assertThrows(BusinessException.class, chamada);

        String mensagemEsperada = "A sessão de votação não está aberta no momento.";
        String mensagemAtual = exception.getMessage();

        assertTrue(mensagemAtual.contains(mensagemEsperada));

    }
}
