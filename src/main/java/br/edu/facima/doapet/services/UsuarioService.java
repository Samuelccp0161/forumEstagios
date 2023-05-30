package br.edu.facima.doapet.services;

import br.edu.facima.doapet.exceptions.UsuarioJaExistenteException;
import br.edu.facima.doapet.model.Usuario;

import java.util.Optional;

public interface UsuarioService {

    void cadastrar(Usuario usuario) throws UsuarioJaExistenteException;

    void logar(String email, String senha);

    Optional<Usuario> getUsuarioLogado();

}
