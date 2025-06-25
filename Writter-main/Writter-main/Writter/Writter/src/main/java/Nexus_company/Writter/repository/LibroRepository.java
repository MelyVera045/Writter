package Nexus_company.Writter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Nexus_company.Writter.model.Libro;
import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    
    Optional findById(Long idLibro); 
    List<Libro> findByAutor(String nombreAutor);
    List<Libro> findByCategoria(String categoria);
    List<Libro> findByTitulo(String titulo);
    List<Libro> findByGenero(String genero);
    
    @Query("SELECT l FROM Libro l WHERE l.autor = :nombreAutor")
    List<Libro> findLibrosByAutor(@Param("nombreAutor") String nombreAutor);

    @Query("SELECT l FROM Libro l WHERE l.genero = :genero")
    List<Libro> findLibrosByGenero(@Param("genero") String genero);
    void deleteById(Long id);

}