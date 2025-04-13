package com.mian.crm.entity;

import java.time.LocalDate;

import com.mian.crm.entity.enums.EstadoTareaEnum;
import com.mian.crm.entity.enums.TipoTareaEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private LocalDate fecha;
    @Enumerated(EnumType.STRING)
    private TipoTareaEnum tipo;

    @Enumerated(EnumType.STRING)
    private EstadoTareaEnum estado;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado asignado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Constructor sin id
    public Tarea(String descripcion, LocalDate fecha, TipoTareaEnum tipo, EstadoTareaEnum estado,
            Empleado asignado, Cliente cliente) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.tipo = tipo;
        this.estado = estado;
        this.asignado = asignado;
        this.cliente = cliente;
    }

}
