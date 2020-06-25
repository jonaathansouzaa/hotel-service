package com.acelera.java.hotel.repository;

import com.acelera.java.hotel.domain.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    public List<Quarto> findByEstaOcupadoFalseAndEstaLimpoFalse();

    public List<Quarto> findByEstaOcupadoFalseAndEstaLimpoTrue();
}
