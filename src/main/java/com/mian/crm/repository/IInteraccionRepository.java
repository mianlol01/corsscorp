package com.mian.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mian.crm.entity.Interaccion;

@Repository
public interface IInteraccionRepository extends JpaRepository<Interaccion, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
