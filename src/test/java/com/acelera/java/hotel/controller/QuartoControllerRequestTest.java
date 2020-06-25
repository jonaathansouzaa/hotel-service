package com.acelera.java.hotel.controller;

import com.acelera.java.hotel.domain.Quarto;
import com.acelera.java.hotel.repository.QuartoRepository;
import com.acelera.java.hotel.service.QuartoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = QuartoController.class)
public class QuartoControllerRequestTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuartoService quartoService;

    @MockBean
    private QuartoRepository quartoRepository;

    @Test
    public void deveRetornarOsQuartosParaLimpezaQueVemDaServiceAtravesDoEndpoint() throws Exception {
        // entrada
        String endpoint = "/quartos-para-limpeza";
        Quarto quarto_1204 = new Quarto(1204, 12, "Duplex", Boolean.FALSE, Boolean.FALSE);
        // Quarto quarto_502 = new Quarto(502, 5, "Triplex", Boolean.FALSE, Boolean.FALSE);
        // List<Quarto> listaDeQuartos = Arrays.asList(quarto_1204, quarto_502);
        List<Quarto> listaDeQuartos = Arrays.asList(quarto_1204);

        // mock
        when(quartoService.getListaDeQuartosParaLimpeza()).thenReturn(listaDeQuartos);

        // execução - validação
        mockMvc.perform(get(endpoint)
                //.content(objectMapper.writeValueAsString(quarto_1204))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numero", is(quarto_1204.getNumero())))
                .andExpect(jsonPath("$[0].andar", is(quarto_1204.getAndar())))
                .andExpect(jsonPath("$[0].tipoQuarto", is(quarto_1204.getTipoQuarto())))
                .andExpect(jsonPath("$[0].estaOcupado", is(quarto_1204.getEstaOcupado())))
                .andExpect(jsonPath("$[0].estaLimpo", is(quarto_1204.getEstaLimpo())));

        verify(quartoService, times(1)).getListaDeQuartosParaLimpeza();
    }

    @Test
    public void testeUsuarioNovo() throws Exception {
        // entrada
        Quarto quarto_1204 = new Quarto(1204, 12, "Duplex", Boolean.FALSE, Boolean.FALSE);
        String endpoint = "/quarto";

        // mock
        doNothing().when(quartoService).salvar(any(Quarto.class));

        // execução - validação
        mockMvc.perform(post(endpoint)
                .content(objectMapper.writeValueAsString(quarto_1204))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(quartoService, times(1)).salvar(any(Quarto.class));
    }

}
