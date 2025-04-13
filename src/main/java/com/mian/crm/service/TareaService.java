package com.mian.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mian.crm.dto.TareaDTO;
import com.mian.crm.dto.TareaMergeDTO;
import com.mian.crm.entity.Cliente;
import com.mian.crm.entity.Tarea;
import com.mian.crm.repository.IClienteRepository;
import com.mian.crm.repository.IEmpleadoRepository;
import com.mian.crm.repository.ITareaRepository;

@Service
public class TareaService {

    @Autowired
    private ITareaRepository tareaRepository;
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IEmpleadoRepository empleadoRepository;

    public TareaDTO saveTarea(TareaMergeDTO tareaDTO) {
        return convertirADTO(tareaRepository.save(convertirAEntidad(tareaDTO)));
    }

    public int deleteTarea(Long id) {
        if (tareaRepository.findById(id).isEmpty()) {
            return 0;
        }
        tareaRepository.deleteById(id);
        return 1;
    }

    public TareaDTO findTareaById(Long id) {
        if (tareaRepository.findById(id).isEmpty()) {
            return null;
        }
        return convertirADTO(tareaRepository.findById(id).orElse(null));
    }

    public TareaDTO updateTarea(TareaMergeDTO tareaDTO, Long id) {
        if (tareaRepository.findById(id).isEmpty()) {
            return null;
        }
        Tarea tareaUpdate = convertirAEntidad(tareaDTO);
        tareaUpdate.setId(id);
        return convertirADTO(tareaRepository.save(tareaUpdate));
    }

    public List<TareaDTO> findAllTareas() {
        return tareaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<TareaDTO> findTareasByClienteId(Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        if (cliente == null) {
            return null;
        }
        return tareaRepository.findAll().stream()
                .filter(tarea -> tarea.getCliente().getId().equals(cliente.getId()))
                .map(this::convertirADTO)
                .toList();
    }

    public List<TareaDTO> findTareasByEmpleadoId(Long idEmpleado) {
        if (empleadoRepository.findById(idEmpleado).isEmpty()) {
            return null;
        }
        return tareaRepository.findAll().stream()
                .filter(tarea -> tarea.getAsignado().getId().equals(idEmpleado))
                .map(this::convertirADTO)
                .toList();
    }

    // Convertir TareaDTO a Tarea
    private Tarea convertirAEntidad(TareaMergeDTO tareaDTO) {
        Tarea tarea = new Tarea();
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setFecha(tareaDTO.getFecha());
        tarea.setTipo(tareaDTO.getTipo());
        tarea.setEstado(tareaDTO.getEstado());
        tarea.setCliente(clienteRepository.findById(tareaDTO.getIdCliente()).orElse(null));
        tarea.setAsignado(empleadoRepository.findById(tareaDTO.getIdVendedor()).orElse(null));
        return tarea;
    }

    // Convertir Tarea a TareaDTO

    private TareaDTO convertirADTO(Tarea tarea) {
        TareaDTO tareaDTO = new TareaDTO();
        tareaDTO.setId(tarea.getId());
        tareaDTO.setDescripcion(tarea.getDescripcion());
        tareaDTO.setFecha(tarea.getFecha());
        tareaDTO.setTipo(tarea.getTipo());
        tareaDTO.setEstado(tarea.getEstado());
        tareaDTO.setCliente(tarea.getCliente());
        tareaDTO.setAsignado(tarea.getAsignado());
        return tareaDTO;
    }
}
