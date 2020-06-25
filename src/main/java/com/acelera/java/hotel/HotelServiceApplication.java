package com.acelera.java.hotel;

import com.acelera.java.hotel.domain.Quarto;
import com.acelera.java.hotel.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelServiceApplication implements CommandLineRunner {

	@Autowired
	public QuartoRepository quartoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HotelServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		quartoRepository.save(new Quarto(123, 12, "Duplex", Boolean.FALSE, Boolean.FALSE));
		quartoRepository.save(new Quarto(1415, 14, "Triplex", Boolean.FALSE, Boolean.TRUE));
		quartoRepository.save(new Quarto(1425, 14, "Duplex", Boolean.TRUE, Boolean.FALSE));
		quartoRepository.save(new Quarto(608, 6, "Triplex", Boolean.FALSE, Boolean.FALSE));
		quartoRepository.save(new Quarto(201, 2, "Triplex", Boolean.TRUE, Boolean.TRUE));

		quartoRepository.save(new Quarto(201, 2, "Triplex", Boolean.FALSE, Boolean.TRUE));
		quartoRepository.save(new Quarto(201, 2, "Triplex", Boolean.FALSE, Boolean.TRUE));
	}
}
