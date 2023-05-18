package br.edu.facima.forum.controller;

import br.edu.facima.forum.exceptions.UsuarioJaExistenteException;
import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("cadastrar")
    public void cadastrar(@RequestBody Usuario usuario) throws UsuarioJaExistenteException {
        usuarioService.cadastrar(usuario);
    }
    @PostMapping("login")
    public void logar(String email, String senha){
        usuarioService.logar(email,senha);
    }

    public void comentar(String comentario) {
        usuarioService.comentar(comentario);
    }
}
