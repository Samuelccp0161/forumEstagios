package br.edu.facima.doapet.services.impl;

import br.edu.facima.doapet.exceptions.LoginException;
import br.edu.facima.doapet.exceptions.UsuarioJaExistenteException;
import br.edu.facima.doapet.model.Usuario;
import br.edu.facima.doapet.repository.UsuarioRepository;
import br.edu.facima.doapet.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private Optional<Usuario> usuarioLogado = Optional.empty();

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void cadastrar(Usuario usuario){
        Optional<Usuario> usuarioConsultado = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioConsultado.isPresent()){
            throw new UsuarioJaExistenteException("Usuario já existe");
        }
        usuarioRepository.save(usuario);
    }

    @Override
    public void logar(String email, String senha) {
        Optional<Usuario> usuarioConsultado = usuarioRepository.findByEmail(email);

      if (usuarioConsultado.isPresent() && usuarioConsultado.get().getSenha().equals(senha)) {
          usuarioLogado = usuarioConsultado;
      } else {
        throw new LoginException("Usuario ou senha invalido");
      }
    }

    @Override
    public Optional<Usuario> getUsuarioLogado() {
        return usuarioLogado;
    }

}
