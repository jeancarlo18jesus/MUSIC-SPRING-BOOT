package com.edu.edu_musica_aa2.controllers;






import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.edu_musica_aa2.models.entity.Albumnes;

import com.edu.edu_musica_aa2.models.entity.Canciones;
import com.edu.edu_musica_aa2.models.entity.Generos;
import com.edu.edu_musica_aa2.models.services.ServiciosAlbumnes;
import com.edu.edu_musica_aa2.models.services.ServiciosCanciones;
import com.edu.edu_musica_aa2.models.services.ServiciosGeneros;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping(path = "/canciones")
public class CancionesController {

    @Autowired
    private ServiciosCanciones services ;

    @Autowired
    private ServiciosGeneros servicesGeneros ;

     @Autowired
    private ServiciosAlbumnes servicesAlbumnes ;

    @GetMapping("/home")
    public String getMethodHome(Model modelo) {
        List<Canciones> listadoCanciones = services.findAllCanciones() ;
        modelo.addAttribute("canciones", listadoCanciones) ;
        modelo.addAttribute("title", "CANCIONES | MUSICA") ;
        return "views/canciones/home";
    }

    
    @GetMapping("/registro")
    public String getMethodRegistro(Model modelo) {
        Canciones cancion = new Canciones() ;
        List<Albumnes> albumnes = servicesAlbumnes.findAllAlbumnes() ;
        List<Generos> generos = servicesGeneros.findAllGeneros() ;
        modelo.addAttribute("cancion", cancion) ;
        modelo.addAttribute("albumnes", albumnes) ;
        modelo.addAttribute("generos", generos) ;
        modelo.addAttribute("title", "REGISTRO | CANCIONES") ;
        return "views/canciones/register";
    }


     // POST DE REGITRO
    @PostMapping("/guardar")
    public String guardarCancionesModelo(@Valid @ModelAttribute("cancion") Canciones cancion,BindingResult result,Model modelo, RedirectAttributes attributes) {
        if(result.hasErrors()){
            List<Generos> generos = servicesGeneros.findAllGeneros();
            List<Albumnes> albumnes = servicesAlbumnes.findAllAlbumnes();
            modelo.addAttribute("generos", generos) ;
            modelo.addAttribute("albumnes", albumnes) ;
            return "views/canciones/register";
        }
        
        services.saveCancion(cancion) ;
        var message = "Cancion ' " + cancion.getTitulo() + " ' registrado 😊" ;
        attributes.addFlashAttribute("insertado", message);
        return "redirect:/canciones/home";
    }

    @GetMapping("/editar/{id}")
    public String getMostrarEdicion(@PathVariable Long id, Model modelo) {
        Canciones cancion = services.findByIdCancion(id);
        if (cancion == null) {
            return "redirect:/canciones/home";
        }
    
        List<Generos> generos = servicesGeneros.findAllGeneros();
        List<Albumnes> albumnes = servicesAlbumnes.findAllAlbumnes();
    
        modelo.addAttribute("generos", generos);
        modelo.addAttribute("albumnes", albumnes);
        modelo.addAttribute("cancion", cancion);
        modelo.addAttribute("title", "EDITAR | CANCIONES");
        
        return "views/canciones/editar";
    }
    
    @PostMapping("/actualizar/{id}")
    public String actualizarCancion(@PathVariable Long id, @Valid @ModelAttribute("cancion") Canciones cancionesExistente, BindingResult result, Model modelo, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            List<Generos> generos = servicesGeneros.findAllGeneros();
            List<Albumnes> albumnes = servicesAlbumnes.findAllAlbumnes();
    
            modelo.addAttribute("generos", generos);
            modelo.addAttribute("albumnes", albumnes);
            modelo.addAttribute("title", "EDITAR | CANCIONES");
            
            return "views/canciones/editar";
        }
    
        Canciones cancion = services.findByIdCancion(id);
        if (cancion == null) {
            return "redirect:/canciones/home";
        }
    
        cancion.setTitulo(cancionesExistente.getTitulo());
        cancion.setDuracion(cancionesExistente.getDuracion());
        cancion.setGenero(cancionesExistente.getGenero());
        cancion.setAlbumnes(cancionesExistente.getAlbumnes());
    
        services.updateCancion(cancion);
        var message = "Cancion ' " + cancionesExistente.getTitulo() + " ' actualizada 🫡";
        attributes.addFlashAttribute("actualizado", message);
    
        return "redirect:/canciones/home";
    }
    

    @GetMapping("/eliminar/{id}")
    public String getEliminarCancion(@PathVariable Long id, RedirectAttributes attributes) {
        Canciones cancion = services.findByIdCancion(id) ;
        services.deleteCancion(id) ;  
        var message = "Cancion ' " + cancion.getTitulo() + " ' eliminado 😭" ;
        attributes.addFlashAttribute("eliminado", message);// elimina al estudiante con el id dado
        return "redirect:/canciones/home";

    }
}
