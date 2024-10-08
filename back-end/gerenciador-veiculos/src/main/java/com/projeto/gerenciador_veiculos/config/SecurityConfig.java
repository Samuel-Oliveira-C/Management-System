package com.projeto.gerenciador_veiculos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Desabilita CSRF conforme necessário
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register").permitAll()  // Permite acesso às rotas de login e registro
                .anyRequest().authenticated()  // Exige autenticação para qualquer outra rota
            )
            .formLogin(form -> form
                .loginPage("/login")  // Página customizada de login
                .defaultSuccessUrl("/home", true)  // Redireciona após login bem-sucedido
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")  // Redireciona após logout
                .invalidateHttpSession(true)  // Invalida sessão
                .deleteCookies("JSESSIONID")  // Remove cookie de sessão
                .permitAll()
            )
            .sessionManagement(session -> session
                .maximumSessions(1)  // Limita a uma sessão por usuário
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("password"))  // Criptografa a senha
                .roles("USER")
                .build());

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
