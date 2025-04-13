package com.mian.crm.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mian.crm.entity.Rol;
import com.mian.crm.entity.Tarea;
import com.mian.crm.entity.Usuario;
import com.mian.crm.entity.Cliente;
import com.mian.crm.entity.Contacto;
import com.mian.crm.entity.Empleado;
import com.mian.crm.entity.Interaccion;
import com.mian.crm.entity.Oportunidad;
import com.mian.crm.entity.enums.*;
import com.mian.crm.repository.IClienteRepository;
import com.mian.crm.repository.IContactoRepository;
import com.mian.crm.repository.IEmpleadoRepository;
import com.mian.crm.repository.IInteraccionRepository;
import com.mian.crm.repository.IOportunidadRepository;
import com.mian.crm.repository.IRolRepository;
import com.mian.crm.repository.ITareaRepository;
import com.mian.crm.repository.IUsuarioRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final IRolRepository rolRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IEmpleadoRepository empleadoRepository;
    private final IClienteRepository clienteRepository;
    private final IContactoRepository contactoRepository;
    private final IInteraccionRepository interaccionRepository;
    private final ITareaRepository tareaRepository;
    private final IOportunidadRepository oportunidadRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading data...");
        if (rolRepository.count() == 0) {
            System.out.println("Creating roles...");
            Rol adminRole = new Rol();
            adminRole.setNombre(RolEnum.ROLE_ADMIN);
            rolRepository.save(adminRole);

            Rol vendedorRole = new Rol();
            vendedorRole.setNombre(RolEnum.ROLE_VENDEDOR);
            rolRepository.save(vendedorRole);

            Rol gerenteRole = new Rol();
            gerenteRole.setNombre(RolEnum.ROLE_GERENTE);
            rolRepository.save(gerenteRole);

        }
        if (usuarioRepository.count() == 0) {
            System.out.println("Creating users...");
            Rol adminRole = rolRepository.findByNombre(RolEnum.ROLE_ADMIN).orElseThrow();
            Rol vendedorRole = rolRepository.findByNombre(RolEnum.ROLE_VENDEDOR).orElseThrow();
            Rol gerenteRole = rolRepository.findByNombre(RolEnum.ROLE_GERENTE).orElseThrow();

            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRol(adminRole);

            Usuario uVendedor1 = new Usuario();
            uVendedor1.setUsername("vendedor1");
            uVendedor1.setPassword(passwordEncoder.encode("123456"));
            uVendedor1.setRol(vendedorRole);

            Usuario uVendedor2 = new Usuario();
            uVendedor2.setUsername("vendedor2");
            uVendedor2.setPassword(passwordEncoder.encode("123456"));
            uVendedor2.setRol(vendedorRole);

            Usuario uVendedor3 = new Usuario();
            uVendedor3.setUsername("vendedor3");
            uVendedor3.setPassword(passwordEncoder.encode("123456"));
            uVendedor3.setRol(vendedorRole);

            Usuario uVendedor4 = new Usuario();
            uVendedor4.setUsername("vendedor4");
            uVendedor4.setPassword(passwordEncoder.encode("123456"));
            uVendedor4.setRol(vendedorRole);

            Usuario uVendedor5 = new Usuario();
            uVendedor5.setUsername("vendedor5");
            uVendedor5.setPassword(passwordEncoder.encode("123456"));
            uVendedor5.setRol(vendedorRole);
            
            Usuario gerente = new Usuario();
            gerente.setUsername("gerente");
            gerente.setPassword(passwordEncoder.encode("123456"));
            gerente.setRol(gerenteRole);

            usuarioRepository.save(admin);
            usuarioRepository.save(gerente);
            usuarioRepository.save(uVendedor1);
            usuarioRepository.save(uVendedor2);
            usuarioRepository.save(uVendedor3);
            usuarioRepository.save(uVendedor4);
            usuarioRepository.save(uVendedor5);
            
            Empleado administrador = new Empleado();
            administrador.setNombre("Juan");
            administrador.setApellido("Perez");
            administrador.setTelefono("123456789");
            administrador.setUsuario(admin);

            Empleado gerente1 = new Empleado();
            gerente1.setNombre("Carlos");
            gerente1.setApellido("Lopez");
            gerente1.setTelefono("456789123");
            gerente1.setUsuario(gerente);

            empleadoRepository.save(administrador);
            empleadoRepository.save(gerente1);

            // crear 4 vendedores más
            Empleado vendedor1 = new Empleado();
            vendedor1.setNombre("Ana");
            vendedor1.setApellido("Hernandez");
            vendedor1.setTelefono("321654987");
            vendedor1.setUsuario(uVendedor1);
            empleadoRepository.save(vendedor1);

            Empleado vendedor2 = new Empleado();
            vendedor2.setNombre("Luis");
            vendedor2.setApellido("Kim");
            vendedor2.setTelefono("654321789");
            vendedor2.setUsuario(uVendedor2);
            empleadoRepository.save(vendedor2);

            Empleado vendedor3 = new Empleado();
            vendedor3.setNombre("Elisa");
            vendedor3.setApellido("Vasquez");
            vendedor3.setTelefono("789123456");
            vendedor3.setUsuario(uVendedor3);
            empleadoRepository.save(vendedor3);

            Empleado vendedor4 = new Empleado();
            vendedor4.setNombre("Francisco");
            vendedor4.setApellido("Delgado");
            vendedor4.setTelefono("159753486");
            vendedor4.setUsuario(uVendedor4);
            empleadoRepository.save(vendedor4);

            Empleado vendedor5 = new Empleado();
            vendedor5.setNombre("Maria");
            vendedor5.setApellido("Gomez");
            vendedor5.setTelefono("987654321");
            vendedor5.setUsuario(uVendedor5);
            empleadoRepository.save(vendedor5);

            // Crear 5 clientes
            Cliente cliente1 = new Cliente(
                    "Raul",
                    "Torres",
                    "raul@outlook.com",
                    "963256987",
                    "FranzCORP",
                    EstadoClienteEnum.POTENCIAL, vendedor1);
            Cliente cliente2 = new Cliente(
                    "Elisa",
                    "Espinosa",
                    "maria.gomez@example.com",
                    "987654321",
                    "InnovaSoft",
                    EstadoClienteEnum.POTENCIAL, vendedor2);

            Cliente cliente3 = new Cliente(
                    "Francisco",
                    "Gonzalez",
                    "carlos.lopez@example.com",
                    "456123789",
                    "BuildIt",
                    EstadoClienteEnum.POTENCIAL, vendedor3);

            Cliente cliente4 = new Cliente(
                    "Ana",
                    "Martínez",
                    "ana.martinez@example.com",
                    "789456123",
                    "GreenSolutions",
                    EstadoClienteEnum.POTENCIAL, vendedor4);

            Cliente cliente5 = new Cliente(
                    "Luis",
                    "Hernández",
                    "luis.hernandez@example.com",
                    "321654987",
                    "NextGen",
                    EstadoClienteEnum.ACTIVO, vendedor5);
            clienteRepository.save(cliente1);
            clienteRepository.save(cliente2);
            clienteRepository.save(cliente3);
            clienteRepository.save(cliente4);
            clienteRepository.save(cliente5);

            // Crear Interacciones
            Interaccion interaccion1 = new Interaccion(
                    TipoInteraccionEnum.CORREO,
                    "Seguimiento de propuesta enviada",
                    LocalDateTime.now(),
                    cliente1,
                    vendedor1);
            Interaccion interaccion2 = new Interaccion(
                    TipoInteraccionEnum.CORREO,
                    "Consulta sobre producto",
                    LocalDateTime.now(),
                    cliente2,
                    vendedor2);
            Interaccion interaccion3 = new Interaccion(
                    TipoInteraccionEnum.CORREO,
                    "Reunión para discutir contrato",
                    LocalDateTime.now(),
                    cliente3,
                    vendedor3);
            Interaccion interaccion4 = new Interaccion(
                    TipoInteraccionEnum.CORREO,
                    "Llamada para seguimiento",
                    LocalDateTime.now(),
                    cliente4,
                    vendedor4);
            Interaccion interaccion5 = new Interaccion(
                    TipoInteraccionEnum.CORREO,
                    "Consulta sobre servicio",
                    LocalDateTime.now(),
                    cliente5,
                    vendedor5);
            interaccionRepository.save(interaccion1);
            interaccionRepository.save(interaccion2);
            interaccionRepository.save(interaccion3);
            interaccionRepository.save(interaccion4);
            interaccionRepository.save(interaccion5);

            // Crear Oportunidades
            Oportunidad oportunidad1 = new Oportunidad(
                    "Nueva Plataforma Web",
                    EtapaOportunidadEnum.EN_NEGOCIACION,
                    new BigDecimal("50000.00"),
                    LocalDate.of(2025, 6, 30),
                    cliente1,
                    vendedor1);
            Oportunidad oportunidad2 = new Oportunidad(
                    "Actualización de Software",
                    EtapaOportunidadEnum.EN_NEGOCIACION,
                    new BigDecimal("20000.00"),
                    LocalDate.of(2025, 7, 15),
                    cliente2,
                    vendedor2);
            Oportunidad oportunidad3 = new Oportunidad(
                    "Desarrollo de Aplicación Móvil",
                    EtapaOportunidadEnum.EN_NEGOCIACION,
                    new BigDecimal("30000.00"),
                    LocalDate.of(2025, 8, 1),
                    cliente3,
                    vendedor3);
            Oportunidad oportunidad4 = new Oportunidad(
                    "Implementación de CRM",
                    EtapaOportunidadEnum.EN_NEGOCIACION,
                    new BigDecimal("15000.00"),
                    LocalDate.of(2025, 8, 15),
                    cliente4,
                    vendedor4);
            Oportunidad oportunidad5 = new Oportunidad(
                    "Migración a la Nube",
                    EtapaOportunidadEnum.EN_NEGOCIACION,
                    new BigDecimal("25000.00"),
                    LocalDate.of(2025, 9, 1),
                    cliente5,
                    vendedor5);
            oportunidadRepository.save(oportunidad1);
            oportunidadRepository.save(oportunidad2);
            oportunidadRepository.save(oportunidad3);
            oportunidadRepository.save(oportunidad4);
            oportunidadRepository.save(oportunidad5);

            // Crear Tareas
            Tarea tarea1 = new Tarea(
                    "Revisar contrato del cliente",
                    LocalDate.of(2025, 4, 15),
                    TipoTareaEnum.REUNION,
                    EstadoTareaEnum.PENDIENTE,
                    vendedor1,
                    cliente1);
            Tarea tarea2 = new Tarea(
                    "Enviar propuesta comercial",
                    LocalDate.of(2025, 4, 20),
                    TipoTareaEnum.CORREO,
                    EstadoTareaEnum.PENDIENTE,
                    vendedor2,
                    cliente2);
            Tarea tarea3 = new Tarea(
                    "Preparar presentación para reunión",
                    LocalDate.of(2025, 4, 25),
                    TipoTareaEnum.REUNION,
                    EstadoTareaEnum.PENDIENTE,
                    vendedor3,
                    cliente3);
            Tarea tarea4 = new Tarea(
                    "Llamar para seguimiento de propuesta",
                    LocalDate.of(2025, 4, 30),
                    TipoTareaEnum.LLAMADA,
                    EstadoTareaEnum.PENDIENTE,
                    vendedor4,
                    cliente4);
            Tarea tarea5 = new Tarea(
                    "Enviar información adicional al cliente",
                    LocalDate.of(2025, 5, 5),
                    TipoTareaEnum.CORREO,
                    EstadoTareaEnum.PENDIENTE,
                    vendedor5,
                    cliente5);
            tareaRepository.save(tarea1);
            tareaRepository.save(tarea2);
            tareaRepository.save(tarea3);
            tareaRepository.save(tarea4);
            tareaRepository.save(tarea5);

            // Crear contactos
            Contacto contacto = new Contacto(
                    "Bruno",
                    "Alvarez",
                    "Gerente de Ventas",
                    "bruno.alva@example.com",
                    "123456789",
                    cliente1);
            Contacto contacto2 = new Contacto(
                    "Sofia",
                    "Martinez",
                    "Directora de Marketing",
                    "sof.sof@hot.com",
                    "987654321",
                    cliente2);
            Contacto contacto3 = new Contacto(
                    "Diego",
                    "Gonzalez",
                    "Jefe de Compras",
                    "digo.25@hotmail.com",
                    "456789123",
                    cliente3);
            Contacto contacto4 = new Contacto(
                    "Lucia",
                    "Fernandez",
                    "Coordinadora de Proyectos",
                    "lufe@hotmail.com",
                    "321654987",
                    cliente4);
            Contacto contacto5 = new Contacto(
                    "Mateo",
                    "Ramirez",
                    "Analista de Sistemas",
                    "mara@hotmail.com",
                    "654321789",
                    cliente5);
            contactoRepository.save(contacto);
            contactoRepository.save(contacto2);
            contactoRepository.save(contacto3);
            contactoRepository.save(contacto4);
            contactoRepository.save(contacto5);

            System.out.println("Data loaded successfully!");
        }
    }
}