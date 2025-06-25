package Nexus_company.Writter.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="comentario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Comentario {

    public Comentario(long l, String string, String string2, Object object, Object object2, Object object3) {
        this.idComentario = (int) l;
        this.contenido = string;
        this.fechaComentario = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idComentario;

    @Column(nullable = false, length = 100)
    private String contenido;

    @Column(nullable = false)
    private Date fechaComentario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    @ManyToMany(mappedBy = "comentarios")
    private List<Libro> libros;

}