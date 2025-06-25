package Nexus_company.Writter.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Nexus_company.Writter.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByIdComentario(Integer idComentario);
    List<Comentario> findByUsuario_Id(Integer idUsuario);
    List<Comentario> findComentariosByLibroId(Long libroId);
    Optional<Comentario> findById(long idLibro);
    Object save(Class<Comentario> class1);  

    @Query("""
        SELECT c 
        FROM Comentario c 
        WHERE c.libro.id = :idLibro
    """)
    List<Comentario> findByLibroIdWithQuery(Long idLibro);
}