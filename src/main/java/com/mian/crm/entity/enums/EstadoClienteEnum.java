package com.mian.crm.entity.enums;

public enum EstadoClienteEnum {
    ACTIVO("Cliente activo"),
    INACTIVO("Cliente inactivo"),
    POTENCIAL("Cliente potencial"),
    EN_ESPERA("En espera de seguimiento"),
    CANCELADO("Relación cancelada");

    private final String descripcion;

    EstadoClienteEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}