package com.senai.exerciciosm1s11.controller;

import com.senai.exerciciosm1s11.controller.dto.UsuarioDto;
import com.senai.exerciciosm1s11.model.Usuario;
import com.senai.exerciciosm1s11.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        return ResponseEntity.ok().body(usuarioService.findAllUsuarios());
    }

    @PostMapping("/salvar")
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody UsuarioDto usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword())); // faz a criptografia da senha informada pelo usuário via API
        return ResponseEntity.ok(usuarioService.save(usuario.converteParaUsuario()));
    }

    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String password) {

        Usuario usuario = usuarioService.findByLogin(login);
        boolean valid = encoder.matches(password, usuario.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);

    }

}
