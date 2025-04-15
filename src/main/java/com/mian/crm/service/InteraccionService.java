package com.mian.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mian.crm.dto.InteraccionDTO;
import com.mian.crm.dto.InteraccionMergeDTO;
import com.mian.crm.entity.Interaccion;
import com.mian.crm.repository.IClienteRepository;
import com.mian.crm.repository.IEmpleadoRepository;
import com.mian.crm.repository.IInteraccionRepository;

@Service
public class InteraccionService {

    @Autowired
    private IInteraccionRepository interaccionRepository;
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IEmpleadoRepository empleadoRepository;

    public InteraccionDTO saveInteraccion(InteraccionMergeDTO interaccion) {
        return convertToDTO(interaccionRepository.save(convertToEntity(interaccion)));
    }

    public int deleteInteraccion(Long id) {
        if (interaccionRepository.findById(id).isEmpty()) {
            return 0;
        }
        interaccionRepository.deleteById(id);
        return 1;
    }

    public InteraccionDTO findInteraccionById(Long id) {
        if (interaccionRepository.findById(id).isEmpty()) {
            return null;
        }
        return convertToDTO(interaccionRepository.findById(id).orElse(null));
    }

    public InteraccionDTO updateInteraccion(InteraccionMergeDTO interaccion, Long id) {
        if (interaccionRepository.findById(id).isEmpty()) {
            return null;
        }
        Interaccion interaccionUpdate = convertToEntity(interaccion);
        interaccionUpdate.setId(id);
        return convertToDTO(interaccionRepository.save(interaccionUpdate));
    }

    public List<InteraccionDTO> findAllInteracciones() {
        return interaccionRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<InteraccionDTO> findInteraccionesByClienteId(Long idCliente) {
        return interaccionRepository.findByClienteId(idCliente).stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<InteraccionDTO> findInteraccionesByVendedorId(Long idVendedor) {
        return interaccionRepository.findByVendedorId(idVendedor).stream()
                .map(this::convertToDTO)
                .toList();
    }

    private InteraccionDTO convertToDTO(Interaccion interaccion) {
        InteraccionDTO interaccionDTO = new InteraccionDTO();
        interaccionDTO.setId(interaccion.getId());
        interaccionDTO.setTipo(interaccion.getTipo());
        interaccionDTO.setDetalle(interaccion.getDetalle());
        interaccionDTO.setFecha(interaccion.getFecha());
        interaccionDTO.setCliente(interaccion.getCliente());
        interaccionDTO.setVendedor(interaccion.getVendedor());
        return interaccionDTO;
    }

    private Interaccion convertToEntity(InteraccionMergeDTO interaccionDTO) {
        Interaccion interaccion = new Interaccion();
        interaccion.setTipo(interaccionDTO.getTipo());
        interaccion.setDetalle(interaccionDTO.getDetalle());
        interaccion.setFecha(interaccionDTO.getFecha());
        interaccion.setCliente(clienteRepository.findById(interaccionDTO.getIdCliente()).orElse(null));
        interaccion.setVendedor(empleadoRepository.findById(interaccionDTO.getIdEmpleado()).orElse(null));
        return interaccion;
    }

}
