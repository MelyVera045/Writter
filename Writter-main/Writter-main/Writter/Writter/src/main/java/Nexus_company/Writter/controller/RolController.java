package Nexus_company.Writter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Nexus_company.Writter.model.Rol;
import Nexus_company.Writter.service.RolService;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> listar() {
        List<Rol> roles = rolService.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> buscar(@PathVariable int id) {
        try {
            Rol rol = rolService.findById(id);
            return ResponseEntity.ok(rol);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Rol> guardarRol(@RequestBody Rol newRol) {
        Rol savedRol = rolService.save(newRol);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizar(@PathVariable Long id, @RequestBody Rol updatedRol) {
        try {
            rolService.save(updatedRol);
            return ResponseEntity.ok(updatedRol);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Rol> patchRol(@PathVariable Long id, @RequestBody Rol partialRol) {
        try{
            Rol updatedRol = rolService.patchRol(id, partialRol);
            return ResponseEntity.ok(updatedRol);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long id) {
        try{
            rolService.delete(1L);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}