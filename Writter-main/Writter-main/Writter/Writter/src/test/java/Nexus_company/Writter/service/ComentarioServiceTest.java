package Nexus_company.Writter.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import Nexus_company.Writter.model.Comentario;
import Nexus_company.Writter.repository.ComentarioRepository;
import Nexus_company.Writter.service.ComentarioService;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
public class ComentarioServiceTest {

    @Autowired
    private ComentarioService comentarioService;

    @MockBean
    private ComentarioRepository comentarioRepository;

    private Comentario createComentario() {
        return new Comentario(1L, "[Contenido]", "", null, null, null);
    }

    @Test
    public void testFindById() {
        when(comentarioRepository.findById(1L)).thenReturn(java.util.Optional.of(createComentario()));
        java.util.Optional<Comentario> comentarioOpt = comentarioService.findByIdComentario((int) 1L); 
        assertNotNull(comentarioOpt);
        assertEquals(true, comentarioOpt.isPresent());
        Comentario comentario = comentarioOpt.get();
        assertEquals(1L, comentario.getIdComentario());
    }


    @Test
    public void testSave() {
        Comentario comentario = createComentario();
        when(comentarioRepository.save(any(Comentario.class))).thenReturn(comentario);
        Comentario savedComentario = comentarioService.save(comentario);
        assertNotNull(savedComentario);
        assertEquals(comentario.getIdComentario(), savedComentario.getIdComentario());
    }

    @Test
    public void testPatchComentario() {
        Comentario existingComentario = createComentario();
        Comentario patchData = new Comentario();
        patchData.setContenido("Contenido actualizado");

        when(comentarioRepository.findByIdComentario((int) 1L)).thenReturn(java.util.Arrays.asList(existingComentario));
        when(comentarioRepository.save(any(Comentario.class))).thenReturn(existingComentario);
        Comentario patchedComentario = comentarioService.patchComentario(1L, patchData);
        assertNotNull(patchedComentario);
        assertEquals("Contenido actualizado", patchedComentario.getContenido());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(comentarioRepository).deleteById( 1L);
        comentarioService.delete(1L);
        verify(comentarioRepository, times(1)).deleteById( 1L); 
    }
        @Test
    public void testSalida() {
        System.out.println("El test se está ejecutando");
        assert true;
    }
}
