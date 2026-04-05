package com.example.demo_service.service;

import com.example.demo_service.model.entity.Proveedor;
import com.example.demo_service.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    public List<Proveedor> buscarPorNombre(String nombre) {
        return proveedorRepository.findByNombreContainingIgnoreCase(nombre)
                .map(List::of)
                .orElse(List.of());
    }

    public Proveedor guardarProveedor(Proveedor proveedor) {

        String telefono = proveedor.getTelefono();

        if (telefono == null || !telefono.matches("\\d{10}")) {
            throw new RuntimeException("Lógica de Negocio: El teléfono debe tener exactamente 10 dígitos numéricos.");
        }

        // Aquí podríamos agregar lógica de negocio específica para proveedores
        return proveedorRepository.save(proveedor);
    }

    public void eliminar(Long id) {
        proveedorRepository.deleteById(id);
    }
}
