package com.mian.crm.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.mian.crm.entity.Cliente;
import com.mian.crm.entity.Empleado;
import com.mian.crm.entity.enums.EtapaOportunidadEnum;

import lombok.Data;

@Data
public class OportunidadDTO {

    private Long id;

    private String nombre;
    private EtapaOportunidadEnum etapa;
    private BigDecimal valor;
    private LocalDate fechaCierreEstimada;
    private Cliente cliente;
    private Empleado vendedor;

}
