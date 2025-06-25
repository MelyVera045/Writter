package Nexus_company.Writter.controller.V2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Nexus_company.Writter.assemblers.ComentarioModelAssembler;
import Nexus_company.Writter.controller.ComentarioController;
import Nexus_company.Writter.model.Comentario;
import Nexus_company.Writter.model.Libro;
import Nexus_company.Writter.service.ComentarioService;
import jakarta.persistence.Id;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/comentarios")
public class ComentarioControllerV2 {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private ComentarioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Comentario>>> getAllComentarios() {
        List<EntityModel<Comentario>> comentarios = comentarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (comentarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                comentarios,
                linkTo(methodOn(ComentarioControllerV2.class).getAllComentarios()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Comentario>> getIdComentario(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioService.findByIdComentario(id.intValue());
        if (comentario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(comentario.get()));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Comentario>> createComentario(@RequestBody Comentario comentario) {
        Comentario newComentario = comentarioService.save(comentario);
        return ResponseEntity
                .created(linkTo(methodOn(ComentarioControllerV2.class).getIdComentario((long) newComentario.getIdComentario())).toUri())
                .body(assembler.toModel(newComentario));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Comentario>> updateComentario(@PathVariable long id, @RequestBody Comentario comentario) {
        ((Comentario) comentario).setIdComentario(id);
        Comentario updatedComentario = comentarioService.save(comentario);
        return ResponseEntity.ok(assembler.toModel(updatedComentario));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Comentario>> patchComentario(@PathVariable long id, @RequestBody Comentario comentario) {
        Comentario updatedComentario = comentarioService.patchComentario(id, comentario);
        if (updatedComentario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedComentario));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteComentario(@PathVariable long id) {
        Optional<Comentario> comentario = comentarioService.findByIdComentario((int) 1L);
        if (comentario == null) {
            return ResponseEntity.notFound().build();
        }
        comentarioService.delete(1L);
        return ResponseEntity.noContent().build();
    }
}
