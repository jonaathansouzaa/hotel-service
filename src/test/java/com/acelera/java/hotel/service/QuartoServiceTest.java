package com.acelera.java.hotel.service;

import com.acelera.java.hotel.domain.Quarto;
import com.acelera.java.hotel.exception.QuartoNotFoundException;
import com.acelera.java.hotel.repository.QuartoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class QuartoServiceTest {

    @Mock
    private QuartoRepository quartoRepository;

    @InjectMocks
    private QuartoService quartoService;

    @Test
    void deveRetornarQuartosParaALimpezaQuandoSolicitados() {
        // Entrada
        Quarto quarto_1204 = new Quarto(1204, 12, "Duplex", Boolean.FALSE, Boolean.FALSE);
        Quarto quarto_501 = new Quarto(501, 5, "Triplex", Boolean.FALSE, Boolean.FALSE);
        List<Quarto> esperado = Arrays.asList(quarto_1204, quarto_501);

        // mock do meu comportamento
        when(quartoRepository.findByEstaOcupadoFalseAndEstaLimpoFalse()).thenReturn(esperado);

        // Execução
        List<Quarto> quartosParaLimpeza = quartoService.getListaDeQuartosParaLimpeza();

        // Validação
        assertEquals(2, esperado.size());
        assertEquals(esperado.get(0), quartosParaLimpeza.get(0)); // quarto 1204
        assertEquals(esperado.get(1), quartosParaLimpeza.get(1)); // quarto 501

        verify(quartoRepository, times(1)).findByEstaOcupadoFalseAndEstaLimpoFalse();
    }

    @Test
    void deveRetornarUmaListaVaziaDeQuartosParaALimpezaQuandoSolicitados() {
        // Entrada
        List<Quarto> esperado = Collections.EMPTY_LIST;

        // mock do meu comportamento
        when(quartoRepository.findByEstaOcupadoFalseAndEstaLimpoFalse()).thenReturn(esperado);

        // Execução
        List<Quarto> quartosParaLimpeza = quartoService.getListaDeQuartosParaLimpeza();

        // Validação
        assertTrue(esperado.isEmpty());

        verify(quartoRepository, times(1)).findByEstaOcupadoFalseAndEstaLimpoFalse();
    }

    @Test
    void deveRetornarQuartoLimpoQuandoInformadoQueFoiLimpo() {
        // entrada
        Long quartoId = 10L;
        Quarto quartoEsperado = new Quarto(1205, 12,"Duplex", Boolean.FALSE, Boolean.FALSE);
        quartoEsperado.setId(quartoId);

        // comportamento do mock
        when(quartoRepository.findById(quartoId)).thenReturn(Optional.of(quartoEsperado));
        when(quartoRepository.save(quartoEsperado)).thenReturn(quartoEsperado);

        // execução
        Quarto quartoLimpo = quartoService.atualizaParaQuartoLimpo(quartoId);

        // validação
        assertTrue(quartoLimpo.getEstaLimpo());
        verify(quartoRepository, times(1)).findById(quartoId);
        verify(quartoRepository, times(1)).save(quartoEsperado);
    }

    @Test()
    void deveRetornarUmaExcecaoCasoPasseUmIdNaoExistente() {
        // Entrada
        Long quartoId = 10L;

        // comportamento do mock
        when(quartoRepository.findById(quartoId)).thenReturn(Optional.empty());

        // Execução
        assertThrows(QuartoNotFoundException.class,
                () -> quartoService.atualizaParaQuartoLimpo(quartoId));

        // Validações
        verify(quartoRepository, times(1)).findById(quartoId);
        verifyNoMoreInteractions(quartoRepository);
    }

    @Test
    void deveRetornarQueQuartoFoiMarcadoParaLimpezaAposCheckout() {
        // entrada
        Long quartoId = 1L;
        Quarto quarto = new Quarto(702, 7,"Duplex", Boolean.TRUE, Boolean.TRUE);

        // mock
        when(quartoRepository.findById(quartoId)).thenReturn(Optional.of(quarto));
        when(quartoRepository.save(quarto)).thenReturn(quarto);

        // execução
        Quarto actual = quartoService.marcarParaLimpezaAposCheckout(quartoId);

        // validação
        assertFalse(actual.getEstaOcupado());
        assertFalse(actual.getEstaLimpo());
        verify(quartoRepository, times(1)).findById(quartoId);
        verify(quartoRepository, times(1)).save(quarto);
    }

    @Test()
    void deveRetornarUmaExcecaoCasoQuartoNaoForEncontrado() {
        // entrada
        Long quartoId = 1L;

        //mock
        when(quartoRepository.findById(quartoId)).thenReturn(Optional.empty());

        // execução - validação
        assertThatThrownBy(() -> quartoService.marcarParaLimpezaAposCheckout(quartoId))
            .isInstanceOf(QuartoNotFoundException.class)
            .hasMessage("Quarto 1 não encontrado.");

        verify(quartoRepository).findById(quartoId);
    }

}
