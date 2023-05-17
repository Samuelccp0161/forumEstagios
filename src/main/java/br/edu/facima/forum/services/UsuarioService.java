package br.edu.facima.forum.services;

import br.edu.facima.forum.exceptions.UsuarioJaExistenteException;
import br.edu.facima.forum.model.Usuario;

import java.util.Optional;

public interface UsuarioService {

    void cadastrar(Usuario usuario) throws UsuarioJaExistenteException;

    void logar(String email, String senha);

    Optional<Usuario> getUsuarioLogado();
}
