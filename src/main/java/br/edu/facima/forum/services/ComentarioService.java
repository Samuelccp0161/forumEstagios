package br.edu.facima.forum.services;

import br.edu.facima.forum.model.Comentario;

import java.util.List;

public interface ComentarioService {
    void comentar(Comentario comentario);

    List<Comentario> listarComentariosDoUsuario(String email);
}
