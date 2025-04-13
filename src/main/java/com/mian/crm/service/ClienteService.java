package com.mian.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mian.crm.dto.ClienteDTO;
import com.mian.crm.dto.ClienteMergeDTO;
import com.mian.crm.entity.Cliente;
import com.mian.crm.repository.IClienteRepository;
import com.mian.crm.repository.IEmpleadoRepository;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IEmpleadoRepository empleadoRepository;

    public ClienteDTO saveCliente(ClienteMergeDTO cliente) {
        return convertToDTO(clienteRepository.save(convertToEntity(cliente)));
    }

    public int deleteCliente(Long id) {
        if (clienteRepository.findById(id).isEmpty()) {
            return 0;
        }
        clienteRepository.deleteById(id);
        return 1;
    }

    public ClienteDTO findClienteById(Long id) {
        if (clienteRepository.findById(id).isEmpty()) {
            return null;
        }
        return convertToDTO(clienteRepository.findById(id).orElse(null));
    }

    public ClienteDTO updateCliente(ClienteMergeDTO cliente, Long id) {
        if (clienteRepository.findById(id).isEmpty()) {
            return null;
        }
        Cliente clienteUpdate = convertToEntity(cliente);
        clienteUpdate.setId(id);
        return convertToDTO(clienteRepository.save(clienteUpdate));
    }

    public List<ClienteDTO> findAllClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    // convertir de cliente a clienteDTO
    private ClienteDTO convertToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellido(cliente.getApellido());
        clienteDTO.setCorreo(cliente.getCorreo());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setEmpresa(cliente.getEmpresa());
        clienteDTO.setEstado(cliente.getEstado());
        clienteDTO.setVendedorAsignado(cliente.getVendedorAsignado());
        return clienteDTO;
    }

    // convertir de clienteDTO a cliente
    private Cliente convertToEntity(ClienteMergeDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setEmpresa(clienteDTO.getEmpresa());
        cliente.setEstado(clienteDTO.getEstado());
        cliente.setVendedorAsignado(empleadoRepository.findById(clienteDTO.getVendedorAsignadoId()).orElse(null));
        return cliente;

    }
}