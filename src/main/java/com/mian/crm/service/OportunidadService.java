package com.mian.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mian.crm.dto.OportunidadDTO;
import com.mian.crm.dto.OportunidadMergeDTO;
import com.mian.crm.entity.Oportunidad;
import com.mian.crm.repository.IClienteRepository;
import com.mian.crm.repository.IEmpleadoRepository;
import com.mian.crm.repository.IOportunidadRepository;

@Service
public class OportunidadService {
    @Autowired
    private IOportunidadRepository oportunidadRepository;
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IEmpleadoRepository empleadoRepository;

    public OportunidadDTO saveOportunidad(OportunidadMergeDTO oportunidadDTO) {
        Oportunidad oportunidad = convertToEntity(oportunidadDTO);
        oportunidad = oportunidadRepository.save(oportunidad);
        return convertToDTO(oportunidad);
    }

    public OportunidadDTO findOportunidadById(Long id) {
        Oportunidad oportunidad = oportunidadRepository.findById(id).orElse(null);
        if (oportunidad == null) {
            return null;
        }
        return convertToDTO(oportunidad);
    }

    public List<OportunidadDTO> findAllOportunidades() {
        return oportunidadRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public int deleteOportunidad(Long id) {
        Oportunidad oportunidad = oportunidadRepository.findById(id).orElse(null);
        if (oportunidad == null) {
            return 0;
        }
        oportunidadRepository.delete(oportunidad);
        return 1;
    }

    public List<OportunidadDTO> findOportunidadesByVendedorId(Long vendedorId) {
        return oportunidadRepository.findByVendedorId(vendedorId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<OportunidadDTO> findOportunidadesByClienteId(Long clienteId) {
        return oportunidadRepository.findByClienteId(clienteId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<OportunidadDTO> findOportunidadesByEtapa(String etapa) {
        return oportunidadRepository.findByEtapa(etapa)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public OportunidadDTO updateOportunidad(OportunidadMergeDTO oportunidadDTO, Long id) {
        Oportunidad oportunidad = oportunidadRepository.findById(id).orElse(null);
        if (oportunidad == null) {
            return null;
        }
        Oportunidad oportunidadUpdate = convertToEntity(oportunidadDTO);
        oportunidadUpdate.setId(id);
        return convertToDTO(oportunidadRepository.save(oportunidadUpdate));

    }

    private OportunidadDTO convertToDTO(Oportunidad oportunidad) {
        OportunidadDTO oportunidadDTO = new OportunidadDTO();
        oportunidadDTO.setId(oportunidad.getId());
        oportunidadDTO.setNombre(oportunidad.getNombre());
        oportunidadDTO.setEtapa(oportunidad.getEtapa());
        oportunidadDTO.setValor(oportunidad.getValor());
        oportunidadDTO.setFechaCierreEstimada(oportunidad.getFechaCierreEstimada());
        oportunidadDTO.setCliente(oportunidad.getCliente());
        oportunidadDTO.setVendedor(oportunidad.getVendedor());
        return oportunidadDTO;
    }

    private Oportunidad convertToEntity(OportunidadMergeDTO oportunidadDTO) {
        Oportunidad oportunidad = new Oportunidad();
        oportunidad.setNombre(oportunidadDTO.getNombre());
        oportunidad.setEtapa(oportunidadDTO.getEtapa());
        oportunidad.setValor(oportunidadDTO.getValor());
        oportunidad.setFechaCierreEstimada(oportunidadDTO.getFechaCierreEstimada());
        oportunidad.setCliente(clienteRepository.findById(oportunidadDTO.getIdCliente()).orElse(null));
        oportunidad.setVendedor(empleadoRepository.findById(oportunidadDTO.getIdVendedor()).orElse(null));
        return oportunidad;
    }
}