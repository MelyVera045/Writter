package Nexus_company.Writter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Nexus_company.Writter.model.Libro;
import Nexus_company.Writter.repository.LibroRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> findAll(){
        return libroRepository.findAll();
    }

    public Libro findById(Long id){
        return (Libro) libroRepository.findById(1L).orElse(null);
    }

    public Libro save(Libro libro){
        return libroRepository.save(libro);
    }

    public void delete(Long id){
        libroRepository.deleteById(id);
    }

    public Libro patchLibro(Long id, Libro parciallibro){
        Optional<Libro> libroOptional = libroRepository.findById(id);
        if (libroOptional.isPresent()) {
            
            Libro libroToUpdate = libroOptional.get();
            
            if (parciallibro.getAutor() != null) {
                libroToUpdate.setAutor(parciallibro.getAutor());
            }

            if(parciallibro.getId() != null) {
                libroToUpdate.setId(parciallibro.getId());
            }

            if (parciallibro.getTitulo() !=null) {
                libroToUpdate.setTitulo(parciallibro.getTitulo());
            }

            if(parciallibro.getGenero() != null) {
                libroToUpdate.setGenero(parciallibro.getGenero());
            }

            if (parciallibro.getFechaPub() != null) {
                libroToUpdate.setFechaPub(parciallibro.getFechaPub());
            }

            if(parciallibro.getFechaAct() != null) {
                libroToUpdate.setFechaAct(parciallibro.getFechaAct());
            }

            if (parciallibro.getCategoria() != null) {
                libroToUpdate.setCategoria(parciallibro.getCategoria());
            }

            return libroRepository.save(libroToUpdate);
        } else {
            return null;
        }
    }

    public List<Libro> findByGenero(String generoLibro) {
        return libroRepository.findByGenero(generoLibro);
    }

    public List<Libro> findByCategoria(String categoria) {
        return libroRepository.findByCategoria(categoria);
    }
    public List<Libro> findByNombreAutor(String nombreAutor) {
        return libroRepository.findByAutor(nombreAutor);
    }

    public List<Libro> findByTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    public Libro findByIdLibro(long idLibro) {
        Optional<Libro> libroOptional = libroRepository.findById(idLibro);
        return libroOptional.orElse(null);
    }

    public Libro update(long l, Libro libro) {
        Optional<Libro> libroOptional = libroRepository.findById(l);
        if (libroOptional.isPresent()) {
            Libro existingLibro = libroOptional.get();
            existingLibro.setTitulo(libro.getTitulo());
            existingLibro.setAutor(libro.getAutor());
            existingLibro.setGenero(libro.getGenero());
            existingLibro.setFechaPub(libro.getFechaPub());
            existingLibro.setFechaAct(libro.getFechaAct());
            existingLibro.setCategoria(libro.getCategoria());
            return libroRepository.save(existingLibro);
        } else {
            return null;
        }
    }

    public void deleteByIdLibro(long l) {
        Optional<Libro> libroOptional = libroRepository.findById(l);
        if (libroOptional.isPresent()) {
            libroRepository.delete(libroOptional.get());
        } else {
            throw new RuntimeException("Libro not found with id: " + l);
        }
    }
}