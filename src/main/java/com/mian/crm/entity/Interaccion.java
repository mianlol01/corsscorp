package com.mian.crm.entity;

import java.time.LocalDateTime;

import com.mian.crm.entity.enums.TipoInteraccionEnum;

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
public class Interaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoInteraccionEnum tipo;
    private String detalle;
    private LocalDateTime fecha;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Empleado vendedor;

    // Constructor sin id
    public Interaccion(TipoInteraccionEnum tipo, String detalle, LocalDateTime fecha, Cliente cliente,
            Empleado vendedor) {
        this.tipo = tipo;
        this.detalle = detalle;
        this.fecha = fecha;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }
}
