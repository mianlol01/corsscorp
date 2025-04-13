package com.mian.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mian.crm.entity.Tarea;

@Repository
public interface ITareaRepository extends JpaRepository<Tarea, Long> {
    // buscar por id de cliente
    Tarea findByClienteId(Long idCliente);

    // buscar por id de empleado
    Tarea findByAsignadoId(Long idEmpleado);

}
