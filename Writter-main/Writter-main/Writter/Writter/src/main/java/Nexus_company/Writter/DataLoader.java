package Nexus_company.Writter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Random;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import Nexus_company.Writter.model.Comentario;
import Nexus_company.Writter.model.Libro;
import Nexus_company.Writter.model.Rol;
import Nexus_company.Writter.model.Usuario;
import Nexus_company.Writter.repository.ComentarioRepository;
import Nexus_company.Writter.repository.LibroRepository;
import Nexus_company.Writter.repository.RolRepository;
import Nexus_company.Writter.repository.UsuarioRepository;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
public void run(String... args) throws Exception {

    net.datafaker.Faker faker = new net.datafaker.Faker(new Locale("es"));
    Random random = new Random();

    // LIBROS
    for (int i = 0; i < 3; i++) {
        Libro libro = new Libro();
        libro.setId(Long.valueOf(i+1));
        libro.setTitulo(faker.book().title());
        libro.setGenero(faker.book().genre());
        libro.setAutor(faker.book().author());
        libro.setFechaPub(new java.sql.Date(faker.date().birthday().getTime()));
        libro.setFechaAct(new java.sql.Date(new Date().getTime()));
        libro.setCategoria(faker.book().genre());
        libroRepository.save(libro);
    }

    // USUARIOS
    for (int i = 0; i < 5; i++) {
        Usuario usuario = new Usuario();
        usuario.setContrasena(faker.internet().password(8, 20));
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setNombre(faker.name().fullName());
        usuarioRepository.save(usuario);
    }

    List<Usuario> usuarios = usuarioRepository.findAll();
    List<Libro> libros = libroRepository.findAll();

    // COMENTARIOS
    for (int i = 0; i < 50; i++) {
        Comentario comentario = new Comentario();
        comentario.setContenido(faker.lorem().maxLengthSentence(90));
        comentario.setFechaComentario(new Date());
        comentario.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
        comentario.setLibro(libros.get(random.nextInt(libros.size())));
        comentarioRepository.save(comentario);
    }

    // ROLES
    for (int i = 0; i < 10; i++) {
        Rol rol = new Rol();
        rol.setNombre(faker.job().title());
        rolRepository.save(rol);
    }
}
}