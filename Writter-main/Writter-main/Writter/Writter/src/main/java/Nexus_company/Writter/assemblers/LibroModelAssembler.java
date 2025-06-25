package Nexus_company.Writter.assemblers;


import Nexus_company.Writter.model.Comentario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import Nexus_company.Writter.model.Libro;
import Nexus_company.Writter.controller.V2.LibroControllerV2;

@Component
public class LibroModelAssembler implements RepresentationModelAssembler<Libro, EntityModel<Libro>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Libro> toModel(Libro libro) {
        return EntityModel.of(libro,
                linkTo(methodOn(LibroControllerV2.class).getLibroById(libro.getId())).withSelfRel(),
                linkTo(methodOn(LibroControllerV2.class).getAllLibros()).withRel("libros"),
                linkTo(methodOn(LibroControllerV2.class).updateLibro(libro.getId(), libro)).withRel("actualizar"),
                linkTo(methodOn(LibroControllerV2.class).deleteLibro(libro.getId())).withRel("eliminar"),
                linkTo(methodOn(LibroControllerV2.class).patchLibro(libro.getId(), libro)).withRel("actualizar-parcial")
        );
    }
}
