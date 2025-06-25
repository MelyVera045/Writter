package Nexus_company.Writter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Nexus_company.Writter.model.Usuario;
import Nexus_company.Writter.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id){
        return (Usuario) usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void delete(Long idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }

    public Usuario patchUsuario(Long id, Usuario parcialusuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            
            Usuario usuarioToUpdate = usuarioOptional.get();
            
            if (parcialusuario.getNombre() != null) {
                usuarioToUpdate.setNombre(parcialusuario.getNombre());
            }

            if(parcialusuario.getId() != null) {
                usuarioToUpdate.setId(parcialusuario.getId());
            }

            if (parcialusuario.getEmail() != null) {
                usuarioToUpdate.setEmail(parcialusuario.getEmail());
            }

            if(parcialusuario.getContrasena() != null) {
                usuarioToUpdate.setContrasena(parcialusuario.getContrasena());
            }

            return usuarioRepository.save(usuarioToUpdate);
        }
        return null;
    }

}