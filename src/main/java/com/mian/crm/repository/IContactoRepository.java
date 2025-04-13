package com.mian.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mian.crm.entity.Contacto;

@Repository
public interface IContactoRepository extends JpaRepository<Contacto, Long> {

    List<Contacto> findByClienteId(Long clienteId);

}
