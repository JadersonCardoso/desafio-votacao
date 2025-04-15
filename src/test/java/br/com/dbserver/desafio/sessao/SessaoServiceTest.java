package br.com.dbserver.desafio.sessao;

import br.com.dbserver.desafio.exception.FileNotFoundException;
import br.com.dbserver.desafio.mapper.MockSessao;
import br.com.dbserver.desafio.pauta.PautaRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    MockSessao input;
    @Mock
    private SessaoMapper mapper;
    @Mock
    private SessaoRepository sessaoRepository;
    @Mock
    private PautaRepository pautaRepository;

    private SessaoService sessaoService;

    @BeforeEach
    void setUp() {
        this.mapper = Mappers.getMapper(SessaoMapper.class);
        this.sessaoService = new SessaoService(this.sessaoRepository, this.pautaRepository, this.mapper);
        this.input = new MockSessao();
    }
    @Test
    @Order(1)
    @DisplayName("Deve Abrir uma sessao com sucesso")
    void executarAberturaSessaoTest() {
        SessaoModel sessao = this.input.mockEntity();

        when(this.pautaRepository.findById(sessao.getPauta().getId())).thenReturn(Optional.of(sessao.getPauta()));
        when(this.sessaoRepository.save(any(SessaoModel.class))).thenReturn(sessao);

        var result = this.sessaoService.executarAberturaSessao(sessao.getPauta().getId(), null);
        assertNotNull(result);
        assertNotNull(result.id());

        Duration duration = Duration.between(result.inicio(), result.fim());
        assertEquals(60L, duration.getSeconds());
    }

    @Test
    @Order(2)
    @DisplayName("Deve retornar erro ao tentar anbrir uma sessao.")
    void executarAberturaSessaoNotFoundExceptionTest () {

        Executable chamada = () ->  this.sessaoService.executarAberturaSessao(UUID.randomUUID(), null);
        Exception exception = assertThrows(FileNotFoundException.class, chamada);

        String mensagemEsperada = "Nâo existe Pauta com o ID informado";
        String mensagemAtual = exception.getMessage();

        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    @Order(3)
    @DisplayName("Deve restornar a lista de sessoes com sucesso")
    void findALLTest() {
        Pageable pageable = PageRequest.of(0,10);
        List<SessaoModel> list = this.input.mockEntityList();
        Page<SessaoModel> page = new PageImpl<>(list, pageable, list.size());

        when(this.sessaoRepository.findAll(pageable)).thenReturn(page);

        Page<SessaoDTO> result = this.sessaoService.findAll(pageable);

        assertEquals(14, result.getTotalElements());

    }




}