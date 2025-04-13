package com.mian.crm.entity.enums;

public enum TipoInteraccionEnum {
    LLAMADA("Llamada telefónica"),
    CORREO("Correo electrónico"),
    REUNION("Reunión presencial"),
    WHATSAPP("Mensaje de WhatsApp"),
    VIDEOLLAMADA("Videollamada"),
    OTRO("Otro tipo de interacción");

    private final String descripcion;

    TipoInteraccionEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}