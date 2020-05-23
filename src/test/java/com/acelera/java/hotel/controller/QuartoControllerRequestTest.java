package com.acelera.java.hotel.controller;

import com.acelera.java.hotel.domain.Quarto;
import com.acelera.java.hotel.service.QuartoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.security.RunAs;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = QuartoController.class)
public class QuartoControllerRequestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuartoService quartoService;

    @Test
    public void deveRetornarOsQuartosParaLimpezaQueVemDaServiceAtravesDoEndpoint() throws Exception {
        // entrada
        String endpoint = "/quartos-para-limpeza";
        Quarto quarto_1204 = new Quarto(1204, 12, "Duplex", Boolean.FALSE, Boolean.FALSE);
        Quarto quarto_502 = new Quarto(502, 5, "Triplex", Boolean.FALSE, Boolean.FALSE);
        List<Quarto> listaDeQuartos = Arrays.asList(quarto_1204, quarto_502);

        // mock
        when(quartoService.getListaDeQuartosParaLimpeza()).thenReturn(listaDeQuartos);

        // execução - validação
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());

        verify(quartoService, times(1)).getListaDeQuartosParaLimpeza();
    }

}
