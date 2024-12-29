package com.edu.edu_musica_aa2.controllers;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.edu_musica_aa2.models.entity.Rol;
import com.edu.edu_musica_aa2.models.entity.Usuario;

import com.edu.edu_musica_aa2.models.repository.RepositoryRole;

import com.edu.edu_musica_aa2.models.services.ServicioUsuario;







@Controller
@Slf4j
public class InicioController {

    @Autowired
    private ServicioUsuario services;

    @Autowired
    private RepositoryRole rolesServices;

    @GetMapping("/login")
    public String getMethodLogin(HttpServletRequest request, Model modelo) {
        // Verifica si el usuario está autenticado
        if (request.getUserPrincipal() != null) {
            // Si el usuario está autenticado, redirige a la página de inicio (home)
            return "redirect:/home";
        }
        modelo.addAttribute("title", "LOGIN | MUSICA");
        // Si no está autenticado, muestra la página de login
        return "login";
    }
   

    @GetMapping("/registroUsuario")
    public String getMethodRegistro(HttpServletRequest request, Model modelo) {
        // Verifica si el usuario está autenticado
        if (request.getUserPrincipal() != null) {
            // Si el usuario está autenticado, redirige a la página de inicio (home)
            return "redirect:/home";
        }
        Usuario usuario = new Usuario();
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("title", "REGISTRO | MUSICA");

        // Si no está autenticado, muestra la página de login
        return "registro";
    }

    /// , RedirectAttributes attribute
    @PostMapping("/registroGuardar")
    public String postGuardarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,
            Model modelo, RedirectAttributes attribute) {
        if (result.hasErrors()) {
            return "registro";
        }
        // Verificar si el username ya existe
        if (services.existePorUsername(usuario.getUsername())) {
            modelo.addAttribute("errorUsername", "El nombre de usuario ya está en uso. Por favor, elige otro.");
            return "registro"; // Volver al formulario con el mensaje de error
        }

        // Encriptar contraseña
        usuario.setPassword(encriptarPassword(usuario.getPassword()));
        usuario.setEnabled(1);

        // Guardar usuario
        Usuario usuarioGuardado = services.save(usuario); // Retorna el usuario con el ID generado

        // Crear y guardar el rol
        Rol rol = new Rol();
        rol.setRol("ROLE_USER");
        rol.setUser_id(usuarioGuardado); // Relacionar con el usuario
        rolesServices.save(rol);

        // Mensaje de éxito
        attribute.addFlashAttribute("succes_registro", "Registro de usuario exitoso 🫡");
        return "redirect:/login";
    }

    public static String encriptarPassword(String password) {
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        return enc.encode(password);
    }

}