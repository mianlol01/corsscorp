package com.mian.crm.dto;

import java.time.LocalDateTime;

import com.mian.crm.entity.Cliente;
import com.mian.crm.entity.Empleado;
import com.mian.crm.entity.enums.TipoInteraccionEnum;

import lombok.Data;

@Data
public class InteraccionDTO {

    private Long id;
    private TipoInteraccionEnum tipo;
    private String detalle;
    private LocalDateTime fecha;
    private Cliente cliente;
    private Empleado vendedor;

}
