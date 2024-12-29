package com.edu.edu_musica_aa2.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;

import org.springframework.web.servlet.support.SessionFlashMapManager;

import com.edu.edu_musica_aa2.models.entity.Usuario;
import com.edu.edu_musica_aa2.models.repository.RepositoryUsuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessMessage extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private RepositoryUsuario userRepository; // Repositorio de usuarios para consultar el estado

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        String inputUsername = request.getParameter("username");
        
        // Busca el usuario en la base de datos
        Usuario user = userRepository.findByUsername(inputUsername);
        SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
        FlashMap flashMap = new FlashMap();

        // if (user == null) {
        //     // Si el usuario no existe en la base de datos (caso raro)
        //     flashMap.put("error", "Usuario no encontrado.");
        //     flashMapManager.saveOutputFlashMap(flashMap, request, response);
        //     response.sendRedirect("/login?error=userNotFound");
        //     return;
        // }

        if (user.getEnabled() == 0) {
            // Si el usuario está deshabilitado, redirige con un mensaje de error
            flashMap.put("error", "Tu cuenta está deshabilitada. Contacta al administrador.");
            flashMapManager.saveOutputFlashMap(flashMap, request, response);
            response.sendRedirect("/login/**");
        }

        // Si el login es exitoso y el usuario está habilitado
        // flashMap.put("success", inputUsername);
        flashMap.put("success", "Bienvenido, login correcto!");
        flashMapManager.saveOutputFlashMap(flashMap, request, response);
        response.sendRedirect("/home"); // Redirige a la página principal
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
