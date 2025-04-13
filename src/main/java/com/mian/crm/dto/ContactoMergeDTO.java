package com.mian.crm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContactoMergeDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    @NotBlank(message = "El cargo no puede estar vacío")
    private String cargo;
    @NotBlank(message = "El email no puede estar vacío")
    private String email;
    @NotBlank(message = "El teléfono no puede estar vacío")
    private String telefono;
    @NotNull(message = "El id del cliente no puede estar vacío")
    private Long idCliente;

}
