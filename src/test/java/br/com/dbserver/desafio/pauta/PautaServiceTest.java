package br.com.dbserver.desafio.pauta;

import br.com.dbserver.desafio.mapper.MockPauta;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    MockPauta input;
    @Mock
    PautaMapper mapper;
    @Mock
    PautaRepository repository;

    PautaService service;


    @BeforeEach
    void setUp() {
        this.mapper = Mappers.getMapper(PautaMapper.class);
        this.service = new PautaService(repository, mapper);
        input = new MockPauta();
    }

    @Test
    @Order(1)
    @DisplayName("Deve salvar uma pauta com sucesso")
    void save() {
        PautaModel pauta = this.input.mockEntity();
        PautaDTO dto = this.mapper.toDto(pauta);

        when(this.repository.save(any(PautaModel.class))).thenReturn(pauta);

        var result = this.service.create(dto);

        assertNotNull(result);
        assertNotNull(result.id());
        assertNotNull(result.titulo());
    }

    @Test
    @Order(2)
    @DisplayName("Deve listar todas as pautas com sucesso")
    void findAllTest() {

        Pageable pageable = PageRequest.of(0,10);
        List<PautaModel> list = this.input.mockEntityList();
        Page<PautaModel> page = new PageImpl<>(list, pageable, list.size());

        when(this.repository.findAll(pageable)).thenReturn(page);

        Page<PautaDTO> result = this.service.findAll(pageable);

        assertEquals(14, result.getTotalElements());
    }
}