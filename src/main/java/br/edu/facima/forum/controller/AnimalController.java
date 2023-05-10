package br.edu.facima.forum.controller;

import br.edu.facima.forum.model.Animal;
import br.edu.facima.forum.services.AnimalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/api/publicar")
    public void publicar(@RequestBody Animal animal) {
        animalService.publicar(animal);
    }
}
