package com.senai.exerciciosm1s11.security;

import com.senai.exerciciosm1s11.service.DetalheUsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity // anotação para indicar que este é um componente de segurança
public class JWTConfiguracao extends WebSecurityConfigurerAdapter {

    @Autowired
    private DetalheUsuarioServiceImpl usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     * Método que configura no spring security a utilização da implementação do serviço de usuário e do passwordEncoder para validar senha.
     * @param auth do gerenciador de autenticação
     * @throws Exception
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
    }

    /**
     * Configura a autenticação das requisições.
     *
     * @param http da requisição
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests(). // csrf resolve problemas de ataques na aplicação. Ficará desativado aqui por estar em desenvolvimento,
                // Em ambiente de produção essa configuração precisa estar ativa.
                        antMatchers(HttpMethod.POST, "/templates/login").permitAll(). // permitir sem requerer autenticação quando acessar o /login
                anyRequest().authenticated(). // todas as demais requisições deve-se exigir autenticação
                and()
                .addFilter(new JWTAutenticarFilter(authenticationManager())) // filtro que realiza a autenticação
                .addFilter(new JWTValidarFilter(authenticationManager())) // filtro que realiza a validação da autenticação
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // para não gravar a sessão do usuário (estado) no servidor
    }

    /**
     * Configuração do cors que é responsável por permitir que a aplicação possa receber requisições de outros domínios.
     * Caso as requisições sejam apenas internas, essa configuração não seria necessária.
     *
     * @return source configuração do cors.
     */

    @Bean
    CorsConfigurationSource corsConfiguration() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration); // liberação para

        return source;
    }

}
