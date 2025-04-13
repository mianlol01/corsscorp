package com.mian.crm.entity.enums;

public enum EstadoTareaEnum {
    PENDIENTE("Pendiente"),
    EN_PROGRESO("En progreso"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada"),
    VENCIDA("Vencida");

    private final String descripcion;

    EstadoTareaEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}