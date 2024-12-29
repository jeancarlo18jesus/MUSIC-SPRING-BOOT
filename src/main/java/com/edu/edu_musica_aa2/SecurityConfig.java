package com.edu.edu_musica_aa2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import com.edu.edu_musica_aa2.models.entity.Usuario;
import com.edu.edu_musica_aa2.models.repository.RepositoryUsuario;
import com.edu.edu_musica_aa2.util.LoginSuccessMessage;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataource;

    @Autowired
    private LoginSuccessMessage loginSuccessMessage;

    @Autowired
    private RepositoryUsuario userRepository; // Repositorio de usuarios para consultar el estado

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/registroUsuario", "/registroGuardar", "/imgs/**", "/home")
                        .permitAll() // Permite acceso a /register , "/css/**", "/js/**", "/images/**"
                        .requestMatchers("/artistas/editar/**", "/artistas/registro/**", "/artistas/eliminar/**",
                                "/albumnes/editar/**", "/albumnes/registro/**", "/albumnes/eliminar/**",
                                "/canciones/editar/**", "/canciones/registro/**", "/canciones/eliminar/**", "/admin/**")
                        .hasRole("ADMIN")
                        .requestMatchers("/", "/artistas/home", "/canciones/home", "/albumnes/home", "/generos/home")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/usuario/**")
                        .hasAnyRole("USER")
                        .anyRequest().authenticated())

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true) // Redirige al usuario después de iniciar sesión
                        .permitAll()
                        .failureHandler(customAuthenticationFailureHandler())
                        .successHandler(loginSuccessMessage))
                .logout(logout -> logout
                        .permitAll()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout"));
        return http.build();
        /*
         * (request, response, authentication) -> {
         * // Si el usuario está autenticado y visita "/login", redirige a "/home"
         * response.sendRedirect("/home");
         * })
         */
    }

    @Autowired
    public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .dataSource(dataource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("SELECT username, password, enabled FROM musica.users WHERE username =?")
                .authoritiesByUsernameQuery(
                        "SELECT u.username, r.rol FROM musica.roles r INNER JOIN musica.users u ON r.user_id = u.id WHERE u.username =?");
        ;
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return (request, response, exception) -> {
            String inputUsername = request.getParameter("username");
            String inputPassword = request.getParameter("password");

            // Busca el usuario en la base de datos
            Usuario user = userRepository.findByUsername(inputUsername);

            String errorMessage = "";

            if (user != null) {
                if (user.getEnabled() == 0) {
                    if(!passwordEncoder.matches(inputPassword, user.getPassword())){
                        errorMessage = "Error al iniciar sesion. Verifica tus credenciales.";
                        response.sendRedirect("/login?enabled=" + errorMessage);
                    }
                    else{
                        errorMessage = "El usuario " + user.getUsername()+ " esta deshabilitado temporalmente.";
                        response.sendRedirect("/login?enabled=" + errorMessage);
                    }

                } else if (exception instanceof BadCredentialsException) {
                    // Si el usuario existe pero las credenciales son incorrectas
                    errorMessage = "Error al iniciar sesion. Verifica tus credenciales.";
                    response.sendRedirect("/login?error=" + errorMessage);
                }
            } else if (exception instanceof BadCredentialsException) {
                // Si el usuario no existe o las credenciales son incorrectas
                errorMessage = "Credenciales incorrectas. Intenta nuevamente.";
                response.sendRedirect("/login?error=" + errorMessage);
            }

            // Guardar el mensaje de error en la sesión
            // Redirigir al login con un parámetro indicando error
        };
    }

}
