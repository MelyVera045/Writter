package Nexus_company.Writter.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Description;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name="libro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 40) 
    private String genero;

    @Column(nullable = false, length = 40)
    private String autor;

    @Column(nullable = false)
    private Date fechaPub;

    @Column(nullable = false)
    private Date fechaAct;

    @Column(nullable = false, length = 30)
    private String categoria;

    @ManyToMany
    @JoinTable(
        name = "libro_comentario",
        joinColumns = @JoinColumn(name = "id_libro"),
        inverseJoinColumns = @JoinColumn(name = "id_comentario")
    )
    private List<Comentario> comentarios;

    @ManyToMany(mappedBy = "libros")
    private List<Usuario> usuarios;

}