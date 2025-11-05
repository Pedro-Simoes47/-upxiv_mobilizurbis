package com.UPX_IV.mobilizaUrbis.controller;

import com.UPX_IV.mobilizaUrbis.entity.Relato;
import com.UPX_IV.mobilizaUrbis.repository.RelatoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdministradorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RelatoRepository relatoRepository;

    @BeforeEach
    void setup() {
        relatoRepository.deleteAll(); // limpa antes do teste

        // cria um relato de teste
        Relato relato = new Relato();
        relato.setTitulo("Teste de Relato");
        relato.setDescricao("Descrição de teste");
        relato.setLocalizacao("Centro");
        relato.setLatitude(new BigDecimal("-23.55"));
        relato.setLongitude(new BigDecimal("-46.63"));
        relato.setDataCriacao(LocalDateTime.now());
        relato.setFotoURL("http://exemplo.com/foto.jpg");
        relatoRepository.save(relato);
    }

    @Test
    void deveListarRelatosComSucesso() throws Exception {
        mockMvc.perform(get("/admin/relatos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // código HTTP 200
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].titulo").value("Teste de Relato"));
    }

    @Test
void deveBuscarRelatoPorIdComSucesso() throws Exception {
    // Cria um relato de teste
    Relato relato = new Relato();
    relato.setTitulo("Relato por ID");
    relato.setDescricao("Descrição específica");
    relato.setLocalizacao("Bairro Central");
    relato.setLatitude(new BigDecimal("-23.55"));
    relato.setLongitude(new BigDecimal("-46.63"));
    relato.setDataCriacao(LocalDateTime.now());
    relato.setFotoURL("http://exemplo.com/foto2.jpg");
    relatoRepository.save(relato);

    // Chama o endpoint GET /admin/relatos/{id}
    mockMvc.perform(get("/admin/relatos/" + relato.getId())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.titulo").value("Relato por ID"))
            .andExpect(jsonPath("$.descricao").value("Descrição específica"));
}

}
