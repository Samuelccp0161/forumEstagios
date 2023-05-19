package br.edu.facima.forum.controller;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.model.Comentario;
import br.edu.facima.forum.services.AnimalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/animais")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("publicar")
    public void publicar(@RequestBody Animal animal) {
        animalService.publicar(animal);
    }

    @GetMapping
    public List<Animal> listar() {
        return animalService.listar();
    }

    public List<Comentario> comentarios() {
        return animalService.comentarios();
    }

    @PostMapping("comentar")
    public void comentar(Comentario comentario) {
        animalService.comentar(comentario);
    }
}
