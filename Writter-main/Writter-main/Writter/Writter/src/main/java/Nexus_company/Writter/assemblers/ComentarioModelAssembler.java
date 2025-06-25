package Nexus_company.Writter.assemblers;

import Nexus_company.Writter.model.Comentario;
import Nexus_company.Writter.controller.V2.ComentarioControllerV2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

@Component
public class ComentarioModelAssembler implements RepresentationModelAssembler<Comentario, EntityModel<Comentario>> {

    @Override
    public EntityModel<Comentario> toModel(Comentario comentario) {
        return EntityModel.of(
                comentario,
                linkTo(methodOn(ComentarioControllerV2.class).getIdComentario((long) comentario.getIdComentario())).withSelfRel(),
                linkTo(methodOn(ComentarioControllerV2.class).getAllComentarios()).withRel("comentarios"),
                linkTo(methodOn(ComentarioControllerV2.class).updateComentario(comentario.getIdComentario(), comentario)).withRel("actualizar"),
                linkTo(methodOn(ComentarioControllerV2.class).deleteComentario(comentario.getIdComentario())).withRel("eliminar"),
                linkTo(methodOn(ComentarioControllerV2.class).patchComentario(comentario.getIdComentario(), comentario)).withRel("actualizar-parcial")
        );
    }
}
