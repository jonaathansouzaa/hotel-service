package com.acelera.java.hotel.controller;

import com.acelera.java.hotel.domain.Quarto;
import com.acelera.java.hotel.service.QuartoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class QuartoControllerUnitTest {

    private QuartoService quartoService = Mockito.mock(QuartoService.class);
    private QuartoController quartoController = new QuartoController(quartoService);

    @Test
    public void deveRetornarOsQuartosParaLimpezaQueVemDaService() {
        // entrada
        Quarto quarto_1204 = new Quarto(1204, 12, "Duplex", Boolean.FALSE, Boolean.FALSE);
        Quarto quarto_502 = new Quarto(502, 5, "Triplex", Boolean.FALSE, Boolean.FALSE);
        List<Quarto> listaDeQuartos = Arrays.asList(quarto_1204, quarto_502);
        ResponseEntity<List<Quarto>> expected = ResponseEntity.ok(listaDeQuartos);

        // mocks
        when(quartoService.getListaDeQuartosParaLimpeza()).thenReturn(listaDeQuartos);

        // execução
        ResponseEntity<List<Quarto>> actual = quartoController.getQuartosParaLimpeza();

        // validação
        assertEquals(expected.getBody(), actual.getBody());
    }

}
