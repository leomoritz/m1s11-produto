package com.senai.exerciciosm1s11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ExerciciosM1s11Application {

    public static void main(String[] args) {
        SpringApplication.run(ExerciciosM1s11Application.class, args);
    }

	/**
	 * Componente da aplicação para encriptar a senha do usuário
	 * @return enconder para criptografia.
	 */
	@Bean
    public PasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

}
