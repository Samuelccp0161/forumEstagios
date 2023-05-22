package br.edu.facima.forum.controller;

import br.edu.facima.forum.exceptions.UsuarioJaExistenteException;
import br.edu.facima.forum.model.Comentario;
import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.services.ComentarioService;
import br.edu.facima.forum.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ComentarioService comentarioService;

    public UsuarioController(UsuarioService usuarioService, ComentarioService comentarioService) {
        this.usuarioService = usuarioService;
        this.comentarioService = comentarioService;
    }

    @PostMapping("cadastrar")
    public void cadastrar(@RequestBody Usuario usuario) throws UsuarioJaExistenteException {
        usuarioService.cadastrar(usuario);
    }
    
    @PostMapping("login")
    public void logar(String email, String senha){
        usuarioService.logar(email,senha);
    }

    @PostMapping("comentar")
    public void comentar(Comentario comentario){
        comentarioService.comentar(comentario);
    }
}
