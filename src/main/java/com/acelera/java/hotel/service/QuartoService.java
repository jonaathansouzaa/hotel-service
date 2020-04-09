package com.acelera.java.hotel.service;

import com.acelera.java.hotel.domain.Quarto;
import com.acelera.java.hotel.exception.QuartoNotFoundException;
import com.acelera.java.hotel.repository.QuartoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {

    private QuartoRepository quartoRepository;

    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    public List<Quarto> getListaDeQuartosParaLimpeza() {
        return quartoRepository.findByEstaOcupadoFalseAndEstaLimpoFalse();
    }

    public void atualizaParaQuartoLimpo(Long quartoId) {
        Optional<Quarto> quartoOptional = quartoRepository.findById(quartoId);
        if (quartoOptional.isPresent()) {
            Quarto quarto = quartoOptional.get();
            quarto.setEstaLimpo(Boolean.TRUE);
            quartoRepository.save(quarto);
        } else {
            throw new QuartoNotFoundException();
        }
    }
}
