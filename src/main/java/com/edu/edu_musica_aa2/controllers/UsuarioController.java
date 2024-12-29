package com.edu.edu_musica_aa2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.edu_musica_aa2.models.entity.Usuario;
import com.edu.edu_musica_aa2.models.repository.RepositoryUsuario;
import com.edu.edu_musica_aa2.models.services.ServicioUsuario;

@Controller
@RequestMapping(path = "/usuario")
public class UsuarioController {

    @Autowired
    private ServicioUsuario services;

    @Autowired
    private RepositoryUsuario servicesRepository;

    @GetMapping("/perfil")
    public String getMethodoPerfil(Model modelo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        modelo.addAttribute("title", "PERFIL | MUSICA");
        modelo.addAttribute("name", username);
        return "views/usuario/home";
    }

    @GetMapping("/editar/{id}")
    public String getMethodoPefil(@PathVariable String id, Model modelo) {
        // implementacion de editar usuario
        if (!services.existePorUsername(id)) {
            return "redirect:/usuario/perfil";
        }
        modelo.addAttribute("title", "EDITAR | PERFIL");
        modelo.addAttribute("nombre", id);
        return "views/usuario/editar";
    }

    @PostMapping("/actualizar")
    public String postMethodName(@RequestParam("name_actual") String name_actual,
            @RequestParam("username") String username, @RequestParam("contraseña_actual") String contraseña_actual,
            @RequestParam("contraseña_nueva") String contraseña_nueva,
            @RequestParam("confirmar_contraseña") String confirmar_contraseña, RedirectAttributes attributes) {
        // Simula la obtención del usuario desde la base de datos
        Usuario usuario = servicesRepository.findByUsername(name_actual);
        // Verificar que la contraseña actual coincide
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(contraseña_actual, usuario.getPassword())) {
            attributes.addFlashAttribute("error", "La contraseña actual es incorrecta.");
            String redirect  = "redirect:/usuario/editar/" + name_actual;
            return redirect;
        }

        // Verificar que la nueva contraseña y la confirmación coincidan
        if (!contraseña_nueva.equals(confirmar_contraseña)) {
            attributes.addFlashAttribute("error", "La nueva contraseña no coincide con la confirmación.");
            String redirect  = "redirect:/usuario/editar/" + name_actual;
            return redirect;
        }

        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(contraseña_nueva));
        services.updateUsuario(usuario);
        attributes.addFlashAttribute("respuesta", " Por favor haz el respectivo logout , para poder visualizar los datos actualizados.");
         return "redirect:/usuario/perfil";
    }

}
