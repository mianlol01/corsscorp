package com.mian.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mian.crm.dto.ContactoDTO;
import com.mian.crm.dto.ContactoMergeDTO;
import com.mian.crm.entity.Contacto;
import com.mian.crm.repository.IClienteRepository;
import com.mian.crm.repository.IContactoRepository;

@Service
public class ContactoService {

    @Autowired
    private IContactoRepository contactoRepository;
    @Autowired
    private IClienteRepository clienteRepository;

    public ContactoDTO saveContacto(ContactoMergeDTO contacto) {
        return convertToDTO(contactoRepository.save(convertToEntity(contacto)));
    }

    public int deleteContacto(Long id) {
        if (contactoRepository.findById(id).isEmpty()) {
            return 0;
        }
        contactoRepository.deleteById(id);
        return 1;
    }

    public ContactoDTO findContactoById(Long id) {
        if (contactoRepository.findById(id).isEmpty()) {
            return null;
        }
        return convertToDTO(contactoRepository.findById(id).orElse(null));
    }

    public ContactoDTO updateContacto(ContactoMergeDTO contacto, Long id) {
        if (contactoRepository.findById(id).isEmpty()) {
            return null;
        }
        Contacto contactoUpdate = convertToEntity(contacto);
        contactoUpdate.setId(id);
        return convertToDTO(contactoRepository.save(contactoUpdate));
    }

    public List<ContactoDTO> findAllContactos() {
        return contactoRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<ContactoDTO> findContactosByClienteId(Long clienteId) {
        return contactoRepository.findByClienteId(clienteId).stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Convertir de contacto a contactoDTO
    private ContactoDTO convertToDTO(Contacto contacto) {
        ContactoDTO contactoDTO = new ContactoDTO();
        contactoDTO.setId(contacto.getId());
        contactoDTO.setApellido(contacto.getApellido());
        contactoDTO.setCargo(contacto.getCargo());
        contactoDTO.setNombre(contacto.getNombre());
        contactoDTO.setEmail(contacto.getEmail());
        contactoDTO.setTelefono(contacto.getTelefono());
        contactoDTO.setCliente(contacto.getCliente());
        return contactoDTO;
    }

    // Convertir de contactoDTO a contacto
    private Contacto convertToEntity(ContactoMergeDTO contactoDTO) {
        Contacto contacto = new Contacto();
        contacto.setApellido(contactoDTO.getApellido());
        contacto.setCargo(contactoDTO.getCargo());
        contacto.setNombre(contactoDTO.getNombre());
        contacto.setEmail(contactoDTO.getEmail());
        contacto.setTelefono(contactoDTO.getTelefono());
        contacto.setCliente(clienteRepository.findById(contactoDTO.getIdCliente()).orElse(null));
        return contacto;
    }
}