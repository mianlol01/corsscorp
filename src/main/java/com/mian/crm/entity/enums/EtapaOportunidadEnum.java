package com.mian.crm.entity.enums;

public enum EtapaOportunidadEnum {
    NUEVA("Nueva oportunidad"),
    EN_NEGOCIACION("En negociación"),
    GANADA("Venta ganada"),
    PERDIDA("Venta perdida"),
    EN_ESPERA("En espera de acción");

    private final String descripcion;

    EtapaOportunidadEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}