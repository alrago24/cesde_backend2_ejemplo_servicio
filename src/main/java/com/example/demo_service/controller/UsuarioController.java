package com.example.demo_service.controller;

import com.example.demo_service.model.entity.Usuario;
import com.example.demo_service.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar Usuarios.
 * Expone endpoints HTTP para interactuar con la aplicación.
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear un usuario.
     * El servicio aplicará las validaciones de negocio.
     */
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (RuntimeException e) {
            // Enviamos un mensaje claro si falla la lógica de negocio
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Uso del buscador personalizado por edad.
     */
    @GetMapping("/por-edad")
    public List<Usuario> buscarPorEdad(@RequestParam Integer min, @RequestParam Integer max) {
        return usuarioService.buscarPorEdad(min, max);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
