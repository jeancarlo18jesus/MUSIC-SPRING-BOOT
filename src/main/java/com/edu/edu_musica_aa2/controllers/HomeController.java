package com.edu.edu_musica_aa2.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping(path = "/")
public class HomeController {
    
    @GetMapping("home")
    public String getMethodHome(Model modelo, @AuthenticationPrincipal User user) {
        //  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // String username = authentication.getName();

        // // Enviar el nombre del usuario a la vista
        // model.addAttribute("username", username);
        // modelo.addAttribute("nombreUsuario","nombreUsuario");
        modelo.addAttribute("title", "ABOUT | MUSICA") ;
        return "index";
    }

    
    @GetMapping("")
    public String getMethodPresentacion(Model modelo) {
        modelo.addAttribute("title", "INICIO | INICIO") ;
        return "inicio";
    }
    @GetMapping("tool")
    public String getMethodoTools(Model modelo) {
        modelo.addAttribute("title", "INICIO") ;
        return "tool_home";
    }
    
}
