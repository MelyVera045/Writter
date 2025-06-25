package Nexus_company.Writter.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.List; 

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Nexus_company.Writter.model.Libro;
import Nexus_company.Writter.repository.LibroRepository;
import Nexus_company.Writter.service.LibroService;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class LibroServiceTest {
    @Autowired
    private LibroService libroService;

    @Autowired
    private LibroRepository libroRepository;

    private Libro createLibro() {
        Libro libro = new Libro();
        libro.setId(1L);
        libro.setTitulo("Titulo");
        libro.setAutor("Autor");
        libro.setGenero("Genero");
        return libro;
    }

    @Test
    public void testFindByAll() {
        when(libroRepository.findAll()).thenReturn(List.of(createLibro()));
        List<Libro> libros = libroService.findAll();
        assertNotNull(libros);
        assertEquals(1, libros.size());
    }

    @Test
    public void testFindById() {
        when(libroRepository.findById(1L)).thenReturn(java.util.Optional.of(createLibro()));
        Libro libro = libroService.findById(1L);
        assertNotNull(libro);
        assertEquals("001", libro.getId());
    }

    @Test
    public void testSave() {
        Libro libro = createLibro();
        when(libroRepository.save(libro)).thenReturn(libro);
        Libro savedLibro = libroService.save(libro);
        assertNotNull(savedLibro);
        assertEquals(libro.getId(), savedLibro.getId());
    }

    @Test
    public void testPatchLibro() {
        Libro existingLibro = createLibro();
        Libro patchData = new Libro();
        patchData.setTitulo("Nuevo Titulo");

        when(libroRepository.findById(1L)).thenReturn(java.util.Optional.of(existingLibro));
        when(libroRepository.save(existingLibro)).thenReturn(existingLibro);

        Libro patchedLibro = libroService.patchLibro(1L, patchData);
        assertNotNull(patchedLibro);
        assertEquals("Nuevo Titulo", patchedLibro.getTitulo());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(libroRepository).deleteById(1L);
        libroService.delete(1L);
        verify(libroRepository, times(1)).deleteById(1L);
    }
    
        @Test
    public void testSalida() {
        System.out.println("El test se está ejecutando");
        assert true;
    }
}