package com.mian.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mian.crm.entity.Interaccion;

@Repository
public interface IInteraccionRepository extends JpaRepository<Interaccion, Long> {

    // Buscar interacciones por cliente
    List<Interaccion> findByClienteId(Long idCliente);

    // Buscar interacciones por vendedor
    List<Interaccion> findByVendedorId(Long idVendedor);
}