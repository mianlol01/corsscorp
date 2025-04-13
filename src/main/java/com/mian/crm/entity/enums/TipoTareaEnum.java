package com.mian.crm.entity.enums;

public enum TipoTareaEnum {
    LLAMADA("Llamada de seguimiento"),
    CORREO("Enviar correo"),
    REUNION("Reunión con cliente"),
    PRESENTACION("Presentación de propuesta"),
    RECORDATORIO("Recordatorio interno"),
    OTRO("Otro tipo de tarea");

    private final String descripcion;

    TipoTareaEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}