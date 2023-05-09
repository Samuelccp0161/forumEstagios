package br.edu.facima.forum.services;

import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.repository.UsuarioRepository;
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
    public void cadastrar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void logar(String email, String senha) {
        Optional<Usuario> usuarioConsultado = usuarioRepository.findByEmail(email);
        if (usuarioConsultado.isPresent()) {
            Usuario usuario = usuarioConsultado.get();

            if (usuario.getSenha().equals(senha)) {
                usuarioLogado = usuarioConsultado;
            }
        }
    }

    @Override
    public Optional<Usuario> getUsuarioLogado() {
        return usuarioLogado;
    }
}
