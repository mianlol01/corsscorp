package com.mian.crm.entity;

import com.mian.crm.entity.enums.EstadoClienteEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String empresa;
    @Enumerated(EnumType.STRING)
    private EstadoClienteEnum estado;

    @ManyToOne
    private Empleado vendedorAsignado;

    // constructor sin id
    public Cliente(String nombre, String apellido, String correo, String telefono, String empresa,
            EstadoClienteEnum estado, Empleado vendedorAsignado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.empresa = empresa;
        this.estado = estado;
        this.vendedorAsignado = vendedorAsignado;
    }
}
