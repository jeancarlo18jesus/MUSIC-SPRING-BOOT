package com.edu.edu_musica_aa2.controllers;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.edu_musica_aa2.models.entity.Generos;
import com.edu.edu_musica_aa2.models.services.ServiciosGeneros;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping(path = "/generos")
public class GenerosController {

 @Autowired
    private ServiciosGeneros services ;


    @GetMapping("/home")
    public String getMethodHome(Model modelo) {
    List<Generos> listadoGeneros = services.findAllGeneros();
    
    // Ordenamos la lista por el ID de manera ascendente
    listadoGeneros.sort(Comparator.comparing(Generos::getId));
    
    modelo.addAttribute("generos", listadoGeneros);
    modelo.addAttribute("title", "GENEROS | MUSICA");
    return "views/generos/home";
}

}
