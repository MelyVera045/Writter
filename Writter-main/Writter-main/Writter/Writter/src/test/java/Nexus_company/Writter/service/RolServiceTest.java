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

import Nexus_company.Writter.model.Rol;
import Nexus_company.Writter.repository.RolRepository;
import Nexus_company.Writter.service.RolService;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
public class RolServiceTest {

    @Autowired
    private RolService rolService;

    @Autowired
    private RolRepository rolRepository;

    private Rol createRol() {
        return new Rol();
    }
    @Test
    public void testFindByAll() {
        when(rolRepository.findAll()).thenReturn(List.of(createRol()));
        List<Rol> roles = rolService.findAll();
        assertNotNull(roles);
        assertEquals(1, roles.size());
    }  

    @Test
    public void testFindById() {
        when(rolRepository.findById(1L)).thenReturn(java.util.Optional.of(createRol()));
        Rol rol = rolService.findById((int) 1L);
        assertNotNull(rol);
        assertEquals(1L, (rol).getId());
    }

    @Test
    public void testSave() {
        Rol rol = createRol();
        when(rolRepository.save(rol)).thenReturn(rol);
        Rol savedRol = rolService.save(rol);
        assertNotNull(savedRol);
        assertEquals(rol.getId(), savedRol.getId());
    }

    @Test
    public void testPatchRol() {
        Rol existingRol = createRol();
        Rol patchData = new Rol();
        patchData.setNombre("Nuevo Nombre");

        when(rolRepository.findById(1L)).thenReturn(java.util.Optional.of(existingRol));
        when(rolRepository.save(existingRol)).thenReturn(existingRol);

        Rol patchedRol = rolService.patchRol(1L, patchData);
        assertNotNull(patchedRol);
        assertEquals("Nuevo Nombre", patchedRol.getNombre());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(rolRepository).deleteById(1L);
        rolService.delete((long) 1L);
        verify(rolRepository).deleteById( 1L);
    }

    @Test
    public void testSalida() {
        System.out.println("El test se está ejecutando");
        assert true;
    }
}
