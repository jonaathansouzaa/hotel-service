package com.acelera.java.hotel.controller;

import com.acelera.java.hotel.domain.Quarto;
import com.acelera.java.hotel.exception.QuartoNotFoundException;
import com.acelera.java.hotel.service.QuartoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class QuartoController {

    private QuartoService quartoService;

    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @GetMapping("quartos-para-limpeza")
    public ResponseEntity<List<Quarto>> getQuartosParaLimpeza() {
        List<Quarto> quartosParaLimpeza = quartoService.getListaDeQuartosParaLimpeza();
        return ResponseEntity.ok(quartosParaLimpeza);
    }

    @GetMapping("quartos-limpos-e-disponiveis")
    public ResponseEntity<List<Quarto>> getQuartosLimposEDisponiveis() {
        List<Quarto> quartosLimposDisponiveis = quartoService.getListaDeQuartosLimposDisponiveis();
        return ResponseEntity.ok(quartosLimposDisponiveis);
    }

    @PostMapping("quartos-para-limpeza/{quartoId}/limpo")
    public ResponseEntity<?> limparQuarto(@PathVariable Long quartoId) {
        quartoService.atualizaParaQuartoLimpo(quartoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("quarto")
    public ResponseEntity<?> incluirNovo(@RequestBody Quarto quarto) {
        quartoService.salvar(quarto);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = QuartoNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(QuartoNotFoundException quartoNotFundException) {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse("O quarto informado não existe para limpeza.")
        );
    }

}
