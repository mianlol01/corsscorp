package com.mian.crm.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.mian.crm.entity.enums.EtapaOportunidadEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OportunidadMergeDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotNull(message = "La etapa no puede ser nula")
    private EtapaOportunidadEnum etapa;
    @NotNull(message = "El valor no puede ser nulo")
    @Positive(message = "El valor debe ser positivo")
    private BigDecimal valor;
    @NotNull(message = "La fecha de cierre estimada no puede ser nula")
    private LocalDate fechaCierreEstimada;
    @NotNull(message = "El cliente no puede ser nulo")
    private Long idCliente;
    @NotNull(message = "El vendedor no puede ser nulo")
    private Long idVendedor;

}
