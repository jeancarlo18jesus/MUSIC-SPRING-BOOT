package com.edu.edu_musica_aa2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.edu_musica_aa2.models.entity.Rol;
import com.edu.edu_musica_aa2.models.entity.Usuario;
import com.edu.edu_musica_aa2.models.services.ServicioUsuario;
import com.edu.edu_musica_aa2.models.services.ServiciosRol;

import jakarta.validation.Valid;



@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    @Autowired
    private ServicioUsuario servicioRol ;

    @Autowired
    private ServiciosRol servicioUsuario ;


    @GetMapping("/usuarios")
    public String getMethodoUsuarios(Model modelo) {

        List<Rol> usuarios = servicioUsuario.findAllRoles();
        int activos = 0; 
        int activosAdmin = 0; 
        int activosUsuarios = 0; 
        for (Rol rol : usuarios) {
            if (rol.getUser_id().getEnabled() > 0) {
                activos++;
                if(rol.getRol().equalsIgnoreCase("ROLE_ADMIN") ) {
                    activosAdmin++;
                }
                if(rol.getRol().equalsIgnoreCase("ROLE_USER")) {
                    activosUsuarios++;
                }
            }
        }       
        modelo.addAttribute("usuarios", usuarios);
        modelo.addAttribute("activos", activos);
        modelo.addAttribute("activosAdmin", activosAdmin);
        modelo.addAttribute("activosUsuarios", activosUsuarios);
        modelo.addAttribute("title", "USUARIOS | MUSICA") ;
        return "/views/admin/home";
    }

    @GetMapping("/editar/{id}")
    public String getMostrarEdicion(@PathVariable Long id, Model modelo) {
        Rol usuario = servicioUsuario.findByIdRol(id);
        if (usuario == null) {
            return "redirect:/admin/usuarios";
        }

        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("title", "EDITAR | USUARIOS");
        
        return "views/admin/editar";
    }
    @PostMapping("/actualizar/{id}")
    public String actualizarCancion(@PathVariable Long id, @Valid @ModelAttribute("usuario") Rol rolExistente, BindingResult result, Model modelo, RedirectAttributes attributes) {
        
    
        Rol rol = servicioUsuario.findByIdRol(id);
        rol.setRol(rolExistente.getRol());
        servicioUsuario.updateRol(rol);    


        Usuario usuario = servicioRol.findByIdUsuario(rolExistente.getUser_id().getId());
        usuario.setEnabled(rolExistente.getUser_id().getEnabled());
        servicioRol.updateUsuario(usuario);
        
        String message_update = "Usuario [ " + rolExistente.getUser_id().getUsername() + " ] actualizado exitosamente!!" ;
        attributes.addFlashAttribute("message_update", message_update);
        
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String getEliminarCancion(@PathVariable Long id, RedirectAttributes attributes) {
        Rol rol = servicioUsuario.findByIdRol(id);
        // Usuario cancion = servicioRol.findByIdUsuario(rol.getUser_id().getId()) ;
        servicioUsuario.deleteRol(id) ;  
        servicioRol.deleteUsuario(rol.getUser_id().getId()) ;  
        // var message = "Cancion ' " + cancion.getTitulo() + " ' eliminado 😭" ;
        // attributes.addFlashAttribute("eliminado", message);// elimina al estudiante con el id dado
        return "redirect:/admin/usuarios";

    }

}
