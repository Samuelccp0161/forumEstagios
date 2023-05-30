package br.edu.facima.doapet.services;

import br.edu.facima.doapet.model.Comentario;

import java.util.List;

public interface ComentarioService {
    void comentar(Comentario comentario);

    List<Comentario> listarComentariosDoUsuario(String email);

    List<Comentario> listarComentariosDoAnimal(Long animalId);
}
