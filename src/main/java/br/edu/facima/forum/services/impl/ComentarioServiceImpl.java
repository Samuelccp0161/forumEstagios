package br.edu.facima.forum.services.impl;

import br.edu.facima.forum.model.Comentario;
import br.edu.facima.forum.repository.ComentarioRepository;
import br.edu.facima.forum.services.ComentarioService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
