package Nexus_company.Writter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;
import jakarta.persistence.ManyToMany;

@Entity
@Table(name="rol")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, length = 40)
    private String nombre;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;
}