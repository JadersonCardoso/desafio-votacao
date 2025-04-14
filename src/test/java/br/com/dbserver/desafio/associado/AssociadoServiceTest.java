package br.com.dbserver.desafio.associado;

import br.com.dbserver.desafio.mapper.MockAssociado;
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

import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class AssociadoServiceTest {

    MockAssociado input;
    @Mock
    private AssociadoMapper mapper;
    @Mock
    private AssociadoRepository repository;
    private AssociadoService service;
    @BeforeEach
    void setup() {
        this.mapper = Mappers.getMapper(AssociadoMapper.class);
        this.service = new AssociadoService(repository,mapper);
        this.input = new MockAssociado();
    }

    @Test
    @DisplayName("Deve retornar os associados com sucesso.")
    void finAllTest() {
        Pageable pageable = PageRequest.of(0,10);
        List<AssociadoModel> list = this.input.mockEntityList();

        Page<AssociadoModel> page = new PageImpl<>(list, pageable, list.size());

        when(this.repository.findAll(pageable)).thenReturn(page);

        Page<AssociadoDTO> result = this.service.findAll(pageable);

        Assertions.assertEquals(14, result.getTotalElements());
    }



}