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

import Nexus_company.Writter.model.Libro;
import Nexus_company.Writter.service.LibroService;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> listar() {
        List<Libro> libros = libroService.findAll();
        if (libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libros);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarLibro(@PathVariable Long id) {
        Libro libro = libroService.findById(id);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Libro>> listarPorGenero(@PathVariable String genero) {
        List<Libro> libros = libroService.findByGenero(genero);
        if (libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/autor/{nombreAutor}")
    public ResponseEntity<List<Libro>> listarPorAutor(@PathVariable String nombreAutor) {
        List<Libro> libros = libroService.findByNombreAutor(nombreAutor);
        if (libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Libro>> listarPorTitulo(@PathVariable String titulo) {
        List<Libro> libros = libroService.findByTitulo(titulo);
        if (libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libros);
    }

    @PostMapping
    public ResponseEntity<Libro> guardarLibro(@RequestBody Libro libro) {
        Libro savedLibro = libroService.save(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLibro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        Libro updatedLibro = libroService.patchLibro(id, libro);
        if (updatedLibro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedLibro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        try {
            libroService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editarLibro(@PathVariable Long id, @RequestBody Libro parcialLibro) {
        Libro updatedLibro = libroService.patchLibro(id, parcialLibro);
        if (updatedLibro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedLibro);
    }
}