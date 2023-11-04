package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.Daos.DistribuidorasDAO;
import edu.pucp.gtics.lab11_gtics_20232.Daos.GeneroJuegoDAO;
import edu.pucp.gtics.lab11_gtics_20232.Daos.JuegosDAO;
import edu.pucp.gtics.lab11_gtics_20232.Daos.PlataformasDAO;
import edu.pucp.gtics.lab11_gtics_20232.entity.*;
import edu.pucp.gtics.lab11_gtics_20232.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller

public class JuegosController {

    @Autowired
    JuegosRepository juegosRepository;

    @Autowired
    PlataformasRepository plataformasRepository;

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    GenerosRepository generosRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    JuegosDAO juegosDAO;
    @Autowired
    GeneroJuegoDAO generoJuegoDAO;
    @Autowired
    PlataformasDAO plataformasDAO;
    @Autowired
    DistribuidorasDAO distribuidorasDAO;


    @GetMapping(value = {"/juegos/lista"})
    public String listaJuegos (Model model){
        model.addAttribute("listaJuegos",juegosRepository.findAll());
        //model.addAttribute("listaJuegos",juegosDAO.listaJuegos());
        return "juegos/lista";
    }

    @GetMapping(value = {"", "/", "/vista"})
    public String vistaJuegos ( ...){

    }

    @GetMapping("/juegos/nuevo")
    public String nuevoJuegos(Model model, @ModelAttribute("juego") Juegos juego){
        List<Plataformas> listaPlataformas = plataformasRepository.findAll();
        List<Distribuidoras> listaDistribuidoras = distribuidorasRepository.findAll();
        List<Generos> listaGeneros = generosRepository.findAll();
        model.addAttribute("listaPlataformas", listaPlataformas);
        model.addAttribute("listaDistribuidoras", listaDistribuidoras);
        model.addAttribute("listaGeneros", listaGeneros);
        return "juegos/editarFrm";
    }
    /*@GetMapping("/juegos/nuevo1")
    public String nuevoJuego(@ModelAttribute("juego") Juegos juegos, Model model){
        model.addAttribute("listaGeneros",generoJuegoDAO.listaGenero());
        model.addAttribute("listaPlataformias",plataformasDAO.listaPlataformas());
        model.addAttribute("listaDistribuidoras",distribuidorasDAO.listaDistribuidoras());
        return "juegos/editarFrm";
    }*/

    @GetMapping("/juegos/editar")
    public String editarJuegos(@RequestParam("id") int id, Model model){
        Optional<Juegos> opt = juegosRepository.findById(id);
        List<Plataformas> listaPlataformas = plataformasRepository.findAll();
        List<Distribuidoras> listaDistribuidoras = distribuidorasRepository.findAll();
        List<Generos> listaGeneros = generosRepository.findAll();
        if (opt.isPresent()){
            Juegos juego = opt.get();
            model.addAttribute("juego", juego);
            model.addAttribute("listaPlataformas", listaPlataformas);
            model.addAttribute("listaDistribuidoras", listaDistribuidoras);
            model.addAttribute("listaGeneros", listaGeneros);
            return "juegos/editarFrm";
        }else {
            return "redirect:/juegos/lista";
        }
    }

    @PostMapping("/juegos/guardar")
    public String guardarJuegos(Model model, RedirectAttributes attr, @ModelAttribute("juego") @Valid Juegos juego, BindingResult bindingResult ){
        if(bindingResult.hasErrors( )){
            List<Plataformas> listaPlataformas = plataformasRepository.findAll();
            List<Distribuidoras> listaDistribuidoras = distribuidorasRepository.findAll();
            List<Generos> listaGeneros = generosRepository.findAll();
            model.addAttribute("juego", juego);
            model.addAttribute("listaPlataformas", listaPlataformas);
            model.addAttribute("listaDistribuidoras", listaDistribuidoras);
            model.addAttribute("listaGeneros", listaGeneros);
            return "juegos/editarFrm";
        } else {
            if (juego.getIdjuego() == 0) {
                attr.addFlashAttribute("msg", "Juego creado exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Juego actualizado exitosamente");
            }
            juegosRepository.save(juego);
            return "redirect:/juegos/lista";
        }


    }
    @PostMapping("juegos/guardar")
    public String guardarJuego(@ModelAttribute("juego") @Valid Juegos juegos,BindingResult bindingResult,
                               Model model,RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            model.addAttribute("listaGeneros",generoJuegoDAO.listaGenero());
            model.addAttribute("listaPlataformias",plataformasDAO.listaPlataformas());
            model.addAttribute("listaDistribuidoras",distribuidorasDAO.listaDistribuidoras());
            return "juegos/editarFrm";
        }else{
            String msg="Juego" + (juegos.getIdjuego()==0 ? "creado":"actualizado") + "exitosamente";
            attributes.addFlashAttribute("msg",msg);
            juegosDAO.guardarJuego(juegos);
            return "redirect:/juegos/lista";
        }
    }





    @GetMapping("/juegos/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id){
        Optional<Juegos> opt = juegosRepository.findById(id);
        if (opt.isPresent()) {
            juegosRepository.deleteById(id);
        }
        return "redirect:/juegos/lista";
    }





}
