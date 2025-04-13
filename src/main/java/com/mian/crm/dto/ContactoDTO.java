package com.mian.crm.dto;

import com.mian.crm.entity.Cliente;

import lombok.Data;

@Data
public class ContactoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String cargo;
    private String email;
    private String telefono;
    private Cliente cliente;
}
