package Nexus_company.Writter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Nexus_company.Writter.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional findById (int id);
    Rol findByNombre(String nombre);
    Rol findRolByNombre(String nombre);

    @Query("""
        SELECT r 
        FROM Rol r 
        WHERE r.id = :id
    """)
    Rol findRolById(int id);
    void deleteById(long id);
    Optional<Rol> findById(long l);
}