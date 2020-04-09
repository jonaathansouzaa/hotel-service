package com.acelera.java.hotel.domain;

import javax.persistence.*;

@Entity
@Table(name = "quarto")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numero;

    private Integer andar;

    private String tipoQuarto;

	private Boolean estaOcupado;

	private Boolean estaLimpo;

	public Quarto() {

    }

    public Quarto(Integer numero, Integer andar, String tipoQuarto, Boolean estaOcupado, Boolean estaLimpo) {
        this.numero = numero;
        this.andar = andar;
        this.tipoQuarto = tipoQuarto;
        this.estaOcupado = estaOcupado;
        this.estaLimpo = estaLimpo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getAndar() {
        return andar;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public Boolean getEstaOcupado() {
        return estaOcupado;
    }

    public void setEstaOcupado(Boolean estaOcupado) {
        this.estaOcupado = estaOcupado;
    }

    public Boolean getEstaLimpo() {
        return estaLimpo;
    }

    public void setEstaLimpo(Boolean estaLimpo) {
        this.estaLimpo = estaLimpo;
    }
}
