package br.edu.facima.doapet.services.impl;

import br.edu.facima.doapet.model.Comentario;
import br.edu.facima.doapet.repository.ComentarioRepository;
import br.edu.facima.doapet.services.ComentarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }
    @Override
    public void comentar(Comentario comentario) {
        comentarioRepository.save(comentario);
    }
    @Override
    public List<Comentario> listarComentariosDoUsuario(String email) {
        return comentarioRepository.findByAutorEmail(email);
    }

    @Override
    public List<Comentario> listarComentariosDoAnimal(Long animalId) {
        return comentarioRepository.findByAnimalId(animalId);
    }
    @Override
    public void deletar(Long id) {
        comentarioRepository.deleteById(id);
    }
}
