package com.mian.crm.dto;

import com.mian.crm.entity.enums.EstadoClienteEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteMergeDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    private String correo;
    @NotBlank(message = "El teléfono no puede estar vacío")
    private String telefono;
    @NotBlank(message = "La empresa no puede estar vacía")
    private String empresa;
    @NotNull(message = "El estado no puede ser nulo")
    private EstadoClienteEnum estado;
    @NotNull(message = "El vendedor asignado no puede ser nulo")
    private Long vendedorAsignadoId;

}
