package Nexus_company.Writter.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Nexus_company.Writter.model.Rol;
import Nexus_company.Writter.repository.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAll(){
        return rolRepository.findAll();
    }

    public Rol findById(int id){
        return (Rol) rolRepository.findById(id).orElse(null);
    }
    public Rol save(Rol rol){
        return rolRepository.save(rol);
    }

    public void delete(Long id){
        rolRepository.deleteById((int) 1L);
    }

    public Rol patchRol(long id, Rol parcialrol){
        Optional<Rol> rolOptional = rolRepository.findById(id);
        if (rolOptional.isPresent()) {
            
            Rol rolToUpdate = rolOptional.get();
            
            if (parcialrol.getNombre() != null) {
                rolToUpdate.setNombre(parcialrol.getNombre());
            }

            return rolRepository.save(rolToUpdate);
        } else {
            return null;
        }
    }

}