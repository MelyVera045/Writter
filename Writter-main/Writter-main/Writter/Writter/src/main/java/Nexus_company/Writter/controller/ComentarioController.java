package Nexus_company.Writter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import Nexus_company.Writter.model.Comentario;
import Nexus_company.Writter.service.ComentarioService;

@RestController
@RequestMapping("/api/v1/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<Comentario>> listar() {
        List<Comentario> comentarios = comentarioService.findAll();
        if (comentarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Comentario>> buscarComentariosPorUsuario(@PathVariable Integer idUsuario) {
        List<Comentario> comentarios = comentarioService.findByUsuario(idUsuario);
        if (comentarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/libro/{idLibro}")
    public ResponseEntity<List<Comentario>> buscarComentariosPorLibro(@PathVariable Long idLibro) {
        List<Comentario> comentarios = comentarioService.findComentariosByLibroId(idLibro);
        if (comentarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Comentario>> buscarComentario(@PathVariable Long id) {
        List<Comentario> comentario = comentarioService.findById(id);
        if (comentario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comentario);
    }

    @PostMapping
    public ResponseEntity<Comentario> createdComentario(@RequestBody Comentario comentario) {
        Comentario createdComentario = comentarioService.save(comentario);
        return ResponseEntity.status(201).body(createdComentario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comentario> editarComentario(@PathVariable int id, @RequestBody Comentario comentario) {
        Comentario comentarioActualizado = comentarioService.patchComentario(id, comentario);
        if (comentarioActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comentarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        comentarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizar(@PathVariable Integer id, @RequestBody Comentario comentario) {
        try {
            comentarioService.save(comentario);
            return ResponseEntity.ok(comentario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}