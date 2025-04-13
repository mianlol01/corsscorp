package com.mian.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mian.crm.entity.Oportunidad;

@Repository
public interface IOportunidadRepository extends JpaRepository<Oportunidad, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
