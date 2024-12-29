package com.edu.edu_musica_aa2.controllers;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.edu_musica_aa2.models.entity.Albumnes;
import com.edu.edu_musica_aa2.models.entity.Artista;
import com.edu.edu_musica_aa2.models.services.ServiciosAlbumnes;
import com.edu.edu_musica_aa2.models.services.ServiciosArtistas;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping(path = "/albumnes")
public class AlbumnesController {


    @Autowired
    private ServiciosAlbumnes services ;

    @Autowired
    private ServiciosArtistas servicesArtistas ;
    @GetMapping("/home")
    public String getMethodHome(Model modelo) {
        List<Albumnes> listadoAlbumnes = services.findAllAlbumnes() ;
        modelo.addAttribute("albumnes", listadoAlbumnes) ;
        modelo.addAttribute("title", "ALBUMNES | MUSICA") ;
        return "views/albumnes/home";
    }
    
    @GetMapping("/registro")
    public String getMethodRegistro(Model modelo) {
        Albumnes albumn = new Albumnes() ;
        List<Artista> artistas = servicesArtistas.findAllArtistas() ;
        modelo.addAttribute("albumn", albumn) ;
        modelo.addAttribute("artistas", artistas) ;
        modelo.addAttribute("title", "REGISTRO | ALBUMNES") ;
        return "views/albumnes/register";
    }


     // POST DE REGITRO
    @PostMapping("/guardar")
    public String guardarAlbumnesModelo(@Valid @ModelAttribute("albumn") Albumnes albumn, BindingResult result,Model modelo, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            // Cargar la lista de artistas para el formulario en caso de error
            List<Artista> artistas = servicesArtistas.findAllArtistas();
            modelo.addAttribute("artistas", artistas);
            return "views/albumnes/register";
        }
        services.saveAlmbun(albumn);
        var message = "Albumn ' " + albumn.getTitulo() + " ' registrado 😊" ;
        attributes.addFlashAttribute("insertado", message);
        return "redirect:/albumnes/home";
    }
    @GetMapping("/editar/{id}")
    public String getMostrarEdicion(@PathVariable Long id, Model modelo) {
        // vamos a validar el id si es que el id ingresado no exites me renderige a views/albumnes/home
        if (services.findByIdAlbumn(id) == null) {
            return "redirect:/albumnes/home";
        }
        List<Artista> artistas = servicesArtistas.findAllArtistas() ;
        modelo.addAttribute("artistas", artistas) ;
        modelo.addAttribute("albumnes", services.findByIdAlbumn(id) );
        return "views/albumnes/editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizarAlbumnesModelo( @PathVariable Long id,@Valid @ModelAttribute Albumnes albumnExistente, BindingResult result,Model modelo, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            // Cargar la lista de artistas para el formulario en caso de error
            List<Artista> artistas = servicesArtistas.findAllArtistas();
            modelo.addAttribute("artistas", artistas);
            modelo.addAttribute("albumnes", albumnExistente);
            return "views/albumnes/editar";
        }
        Albumnes albumn = services.findByIdAlbumn(id) ;
        albumn.setTitulo(albumnExistente.getTitulo());
        albumn.setArtista(albumnExistente.getArtista());
        albumn.setFecha(albumnExistente.getFecha());

        services.updateAlbumn(albumnExistente);

        var message = "Albumn ' " + albumnExistente.getTitulo() + " ' actualizado 🫡" ;

        attributes.addFlashAttribute("actualizado", message);
        return "redirect:/albumnes/home";
    }

    
    
    @GetMapping("/eliminar/{id}")
    public String getEliminarAlbumn(@PathVariable Long id, RedirectAttributes attributes) {
        Albumnes albumn = services.findByIdAlbumn(id) ;
        services.deleteAlbumn(id) ;  
        var message = "Albumn ' " + albumn.getTitulo() + " ' eliminado 😭" ;
        attributes.addFlashAttribute("eliminado", message);// elimina al estudiante con el id dado
        return "redirect:/albumnes/home";

    }


}
