package com.mian.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mian.crm.entity.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    // Buscar clientes por id empleado
    List<Cliente> findByEmpleadoId(Long idEmpleado);
}
