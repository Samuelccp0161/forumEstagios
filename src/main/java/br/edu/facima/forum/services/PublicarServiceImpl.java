package br.edu.facima.forum.services;

import br.edu.facima.forum.model.PublicarAnimal;
import br.edu.facima.forum.repository.PublicarRepository;

public class PublicarServiceImpl implements PublicarService{

    private final PublicarRepository publicarRepository;

    public PublicarServiceImpl(PublicarRepository publicarRepository) {
        this.publicarRepository = publicarRepository;
    }

    @Override
    public void publicar(PublicarAnimal publicarAnimal) {
        publicarRepository.save(publicarAnimal);
    }
}
