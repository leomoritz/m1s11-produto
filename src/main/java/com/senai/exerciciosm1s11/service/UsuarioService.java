package com.senai.exerciciosm1s11.service;

import com.senai.exerciciosm1s11.model.Usuario;
import com.senai.exerciciosm1s11.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario findByLogin(String login){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);

        if(usuarioOptional.isEmpty()){
            throw new RuntimeException("Não foi encontrado um usuário com este login.");
        }

        return usuarioOptional.get();

    }

    public Usuario save(Usuario usuario) {

        if(usuario == null){
            throw new RuntimeException("Usuário não pode ser nulo");
        }

        return usuarioRepository.save(usuario);
    }
}
