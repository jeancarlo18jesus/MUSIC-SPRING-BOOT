package com.edu.edu_musica_aa2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.edu_musica_aa2.models.entity.Artista;
import com.edu.edu_musica_aa2.models.services.ServiciosArtistas;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(path = "/artistas")
public class ArtistasController {

    @Autowired
    private ServiciosArtistas services;

    @GetMapping("/home")
    public String getMethodHome(Model modelo) {
        List<Artista> listadoArtistas = services.findAllArtistas();
        modelo.addAttribute("artistas", listadoArtistas);
        modelo.addAttribute("title", "ARTISTAS | MUSICA");
        return "views/artistas/home";
    }

    @GetMapping("/error-403")
public String forzarError403() {
    return "views/artistas/forbidden";
}

    @GetMapping("/registro")
    public String getMethodRegistro(Model modelo) {
        Artista artista = new Artista();
        modelo.addAttribute("artistas", artista);
        modelo.addAttribute("title", "REGISTRO | ARTISTAS");
        return "views/artistas/register";
    }

    // POST DE REGITRO
    @PostMapping("/guardar")
    public String guardarArtistaModelo(@Valid @ModelAttribute("artistas") Artista artista, BindingResult result,
            Model modelo, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            // Cargar la lista de artistas para el formulario en caso de error
            return "views/artistas/register";
        }
        services.saveArtista(artista);
        var message = "Artista ' " + artista.getNombre() + " ' registrado 😊";
        attributes.addFlashAttribute("insertado", message);
        return "redirect:/artistas/home";
    }

    @GetMapping("/editar/{id}")
    public String getMostrarEdicion(@PathVariable Long id, Model modelo) {
        Artista artista = services.findByIdArtista(id);
        if (artista == null) {
            return "redirect:/artistas/home";
        }
        modelo.addAttribute("artistas", artista);
        modelo.addAttribute("title", "EDITAR | ARTISTAS");
        return "views/artistas/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarArtista(@PathVariable Long id, @Valid @ModelAttribute("artistas") Artista artistasExistente,
            BindingResult result, Model modelo, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("title", "EDITAR | ARTISTAS");
            return "views/artistas/editar";
        }

        Artista artista = services.findByIdArtista(id);
        if (artista == null) {
            return "redirect:/artistas/home";
        }

        artista.setNombre(artistasExistente.getNombre());
        artista.setFecha(artistasExistente.getFecha());
        artista.setNacionalidad(artistasExistente.getNacionalidad());

        services.updateArtista(artista);

        var message = "Artista ' " + artistasExistente.getNombre() + " ' actualizado 🫡";
        attributes.addFlashAttribute("actualizado", message);
        return "redirect:/artistas/home";
    }

    @GetMapping("/eliminar/{id}")
    public String getEliminarArtista(@PathVariable Long id, RedirectAttributes attributes) {
        Artista artista = services.findByIdArtista(id);
        services.deleteArtista(id);
        var message = "Artista ' " + artista.getNombre() + " ' eliminado 😭";
        attributes.addFlashAttribute("eliminado", message);// elimina al estudiante con el id dado
        return "redirect:/artistas/home";

    }

}
