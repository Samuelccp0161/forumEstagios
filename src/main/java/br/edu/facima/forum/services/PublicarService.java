package br.edu.facima.forum.services;

import br.edu.facima.forum.model.PublicarAnimal;
import org.springframework.stereotype.Service;

@Service
public interface PublicarService {

    void publicar(PublicarAnimal publicarAnimal);
}
