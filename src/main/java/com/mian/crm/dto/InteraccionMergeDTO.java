package com.mian.crm.dto;

import java.time.LocalDateTime;

import com.mian.crm.entity.enums.TipoInteraccionEnum;

import lombok.Data;

@Data
public class InteraccionMergeDTO {

    private TipoInteraccionEnum tipo;
    private String detalle;
    private LocalDateTime fecha;
    private Long idCliente;
    private Long idEmpleado;
}
