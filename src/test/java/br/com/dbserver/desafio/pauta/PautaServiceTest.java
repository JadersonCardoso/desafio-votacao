package br.com.dbserver.desafio.pauta;

import br.com.dbserver.desafio.mapper.MockPauta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    void save() {
        PautaModel pauta = this.input.mockEntity();

        PautaDTO dto = this.mapper.toDto(pauta);

        when(this.repository.save(any(PautaModel.class))).thenReturn(pauta);

        var result = this.service.create(dto);

        assertNotNull(result);
        assertNotNull(result.id());
        assertNotNull(result.titulo());
    }
}