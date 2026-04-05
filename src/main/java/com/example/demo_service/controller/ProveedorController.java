package com.example.demo_service.controller;

import com.example.demo_service.model.entity.Proveedor;
import com.example.demo_service.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Controlador REST para gestionar Proveedores.
 * Expone endpoints HTTP para interactuar con la aplicación.
 */
@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> listarTodos() {
        return proveedorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> buscarPorId(@PathVariable Long id) {
        return proveedorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/por-nombre")
    public List<Proveedor> buscarPorNombre(@RequestParam String nombre) {
        return proveedorService.buscarPorNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<?> crearProveedor(@RequestBody Proveedor proveedor) {
        try {
            Proveedor nuevoProveedor = proveedorService.guardarProveedor(proveedor);
            return ResponseEntity.ok(nuevoProveedor);
        } catch (RuntimeException e) {
            // Enviamos un mensaje claro si falla la lógica de negocio
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    

}
