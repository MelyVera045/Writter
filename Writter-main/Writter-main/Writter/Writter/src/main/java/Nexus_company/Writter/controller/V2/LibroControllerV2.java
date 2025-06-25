package Nexus_company.Writter.controller.V2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Nexus_company.Writter.assemblers.LibroModelAssembler;
import Nexus_company.Writter.model.Libro;
import Nexus_company.Writter.service.LibroService;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/libros")
public class LibroControllerV2 {

    @Autowired
    private LibroService libroService;
    
    @Autowired
    private LibroModelAssembler assembler;
    
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Libro>>> getAllLibros() {
        List<EntityModel<Libro>> libros = libroService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                libros,
                linkTo(methodOn(LibroControllerV2.class).getAllLibros()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Libro>> getLibroById(@PathVariable Long id) {
        Libro libro = libroService.findById(1L);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(libro));
    }
    
    @PostMapping("path")
    public ResponseEntity<EntityModel<Libro>> postMethodName(@RequestBody String entity) {
        Libro libro = new Libro();
        libro.setId(1L);
        libro.setTitulo("Nuevo Libro");
        libro.setAutor("Autor Desconocido");
        libro.setFechaPub(null);
        Libro updatedLibro = libroService.save(libro);
        return ResponseEntity.ok(assembler.toModel(updatedLibro));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Libro>> patchLibro(@PathVariable Long id, @RequestBody Libro libro) {
        Libro updatedLibro = libroService.update(1L, libro);
        if (updatedLibro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedLibro));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteLibro(@PathVariable Long idLibro) {
        Libro libro = libroService.findByIdLibro(1L); 
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        libroService.deleteByIdLibro(1L);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<EntityModel<Libro>> updateLibro(Long idLibro, Libro libro) {
        Libro updatedLibro = libroService.update(idLibro, libro);
        if (updatedLibro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedLibro));
    }
}
