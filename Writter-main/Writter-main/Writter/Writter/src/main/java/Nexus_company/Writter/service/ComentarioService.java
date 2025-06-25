package Nexus_company.Writter.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Nexus_company.Writter.model.Comentario;
import Nexus_company.Writter.repository.ComentarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> findAll(){
        return comentarioRepository.findAll();
    }

    public List<Comentario> findById(Long id){
        return comentarioRepository.findByIdComentario(id.intValue());
    }

    public List<Comentario> findByUsuario(Integer idUsuario){
        return comentarioRepository.findByUsuario_Id(idUsuario);
    }

    public Comentario save(Comentario comentario){
        return comentarioRepository.save(comentario);
    }

    public void delete(long idComentario){
        comentarioRepository.deleteById((long) idComentario);
    }

    public Comentario patchComentario(long id, Comentario parcialComentario){
        Optional<Comentario> comentarioOptional = comentarioRepository.findById((long) id);
        if (comentarioOptional.isPresent()) {
            
            Comentario comentarioToUpdate = comentarioOptional.get();
            
            if (parcialComentario.getContenido() != null) {
                comentarioToUpdate.setContenido(parcialComentario.getContenido());
            }
            return comentarioToUpdate;
        } else {
            return null;
        }
    }

    public Optional<Comentario> findByIdComentario(Integer idComentario) {
        return comentarioRepository.findById((long) idComentario);
    }

    public List<Comentario> findComentariosByLibroId(Long idLibro){
        return comentarioRepository.findComentariosByLibroId(idLibro);
    }

}