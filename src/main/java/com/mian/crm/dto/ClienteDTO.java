package com.mian.crm.dto;

import com.mian.crm.entity.Empleado;
import com.mian.crm.entity.enums.EstadoClienteEnum;

import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String empresa;
    private EstadoClienteEnum estado;
    private Empleado vendedorAsignado;
}