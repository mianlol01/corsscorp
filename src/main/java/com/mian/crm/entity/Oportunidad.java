package com.mian.crm.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.mian.crm.entity.enums.EtapaOportunidadEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Oportunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Enumerated(EnumType.STRING)
    private EtapaOportunidadEnum etapa;
    private BigDecimal valor;
    private LocalDate fechaCierreEstimada;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Empleado vendedor;

    // Constructor sin id
    public Oportunidad(String nombre, EtapaOportunidadEnum etapa, BigDecimal valor,
            LocalDate fechaCierreEstimada, Cliente cliente, Empleado vendedor) {
        this.nombre = nombre;
        this.etapa = etapa;
        this.valor = valor;
        this.fechaCierreEstimada = fechaCierreEstimada;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }
}
