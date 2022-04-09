package com.senai.exerciciosm1s11.controller.dto;

import com.senai.exerciciosm1s11.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDto {

    @NotBlank
    private String login;
    @NotBlank
    private String password;

    public Usuario converteParaUsuario(){
        Usuario usuario = new Usuario();
        usuario.setLogin(this.login);
        usuario.setPassword(this.password);
        return usuario;
    }

}
