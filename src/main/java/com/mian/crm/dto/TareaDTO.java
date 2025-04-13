package com.mian.crm.dto;

import java.time.LocalDate;

import com.mian.crm.entity.Cliente;
import com.mian.crm.entity.Empleado;
import com.mian.crm.entity.enums.EstadoTareaEnum;
import com.mian.crm.entity.enums.TipoTareaEnum;

import lombok.Data;

@Data
public class TareaDTO {

    private Long id;
    private String descripcion;
    private LocalDate fecha;
    private TipoTareaEnum tipo;
    private EstadoTareaEnum estado;
    private Empleado asignado;
    private Cliente cliente;
}
