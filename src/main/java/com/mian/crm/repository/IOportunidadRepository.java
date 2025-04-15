package com.mian.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mian.crm.entity.Oportunidad;

@Repository
public interface IOportunidadRepository extends JpaRepository<Oportunidad, Long> {
    // Filtrar por Vendedor
    List<Oportunidad> findByVendedorId(Long vendedorId);

    // Filtrar por Cliente
    List<Oportunidad> findByClienteId(Long clienteId);

    // Filtrar por Etapa
    List<Oportunidad> findByEtapa(String etapa);
}
