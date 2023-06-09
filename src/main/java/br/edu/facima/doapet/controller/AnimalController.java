package br.edu.facima.doapet.controller;

import br.edu.facima.doapet.model.Animal;
import br.edu.facima.doapet.model.Comentario;
import br.edu.facima.doapet.services.AnimalService;
import br.edu.facima.doapet.services.ComentarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/animais")
public class AnimalController {

    private final AnimalService animalService;
    private final ComentarioService comentarioService;

    public AnimalController(AnimalService animalService, ComentarioService comentarioService) {
        this.animalService = animalService;
        this.comentarioService = comentarioService;
    }

    @PostMapping("publicar")
    public void publicar(@RequestBody Animal animal) {
        animalService.publicar(animal);
    }

    @GetMapping
    public List<Animal> listar() {
        return animalService.listar();
    }

    @GetMapping("{animalId}/comentarios")
    public List<Comentario> listarComentarios(@PathVariable Long animalId) {
        return comentarioService.listarComentariosDoAnimal(animalId);
    }
}
