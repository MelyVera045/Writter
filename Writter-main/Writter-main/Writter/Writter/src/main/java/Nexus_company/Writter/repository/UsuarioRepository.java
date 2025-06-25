package Nexus_company.Writter.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Nexus_company.Writter.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario findByEmail(String email);
    @Query("SELECT u FROM Usuario u WHERE u.id = :id")
    Optional findById(Long id);
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    Usuario findByNombre(String nombre);
}