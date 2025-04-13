package com.mian.crm.dto;

import java.time.LocalDate;

import com.mian.crm.entity.enums.EstadoTareaEnum;
import com.mian.crm.entity.enums.TipoTareaEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TareaMergeDTO {

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;
    @NotNull(message = "El tipo de tarea no puede ser nulo")
    private TipoTareaEnum tipo;
    @NotNull(message = "El estado de la tarea no puede ser nulo")
    private EstadoTareaEnum estado;
    @NotNull(message = "El vendedor asignado no puede ser nulo")
    private Long idVendedor;
    @NotNull(message = "El cliente no puede ser nulo")
    private Long idCliente;

}
