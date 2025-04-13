package com.mian.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mian.crm.entity.Empleado;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {
    Empleado findByNombre(String nombre);
}
