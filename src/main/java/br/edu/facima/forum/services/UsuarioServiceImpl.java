package br.edu.facima.forum.services;

import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void cadastrar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
