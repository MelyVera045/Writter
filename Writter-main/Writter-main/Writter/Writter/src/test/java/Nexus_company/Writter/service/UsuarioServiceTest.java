package Nexus_company.Writter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Nexus_company.Writter.model.Usuario;
import Nexus_company.Writter.repository.UsuarioRepository;
import Nexus_company.Writter.service.UsuarioService;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    private Usuario createUsuario() {
        return new Usuario();
    }
    @Test
    public void testFindByAll() {
        when(usuarioRepository.findAll()).thenReturn(List.of(createUsuario()));
        List<Usuario> usuarios = usuarioService.findAll();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }  

    @Test
    public void testFindById() {
        when(usuarioRepository.findById(1L)).thenReturn(java.util.Optional.of(createUsuario()));
        Usuario usuario = usuarioService.findById( 1L);
        assertNotNull(usuario);
        assertEquals(Long.valueOf(1L), usuario.getId());
    }

    @Test
    public void testSave() {
        Usuario usuario = createUsuario();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario savedUsuario = usuarioService.save(usuario);
        assertNotNull(usuario.getId());
        assertEquals(usuario.getId(), savedUsuario.getId());
    }

    @Test
    public void testPatchUsuario() {
        Usuario existingUsuario = createUsuario();
        Usuario patchData = new Usuario();
        patchData.setNombre("Nuevo Nombre");

        when(usuarioRepository.findById(1L)).thenReturn(java.util.Optional.of(existingUsuario));
        when(usuarioRepository.save(existingUsuario)).thenReturn(existingUsuario);

        Usuario patchedUsuario = usuarioService.patchUsuario(1L, patchData);
        assertNotNull(patchedUsuario);
        assertEquals("Nuevo Nombre", patchedUsuario.getNombre());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(usuarioRepository).deleteById( 1L);
        usuarioService.delete((long) 1L);
        verify(usuarioRepository).deleteById( 1L);
    }

    @Test
    public void testSalida() {
        System.out.println("El test se está ejecutando");
        assert true;
    }
}
