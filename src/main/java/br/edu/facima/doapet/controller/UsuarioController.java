package br.edu.facima.doapet.controller;

import br.edu.facima.doapet.exceptions.UsuarioJaExistenteException;
import br.edu.facima.doapet.model.Comentario;
import br.edu.facima.doapet.model.Usuario;
import br.edu.facima.doapet.services.ComentarioService;
import br.edu.facima.doapet.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void logar(@RequestBody Usuario usuario){
        usuarioService.logar(usuario.getEmail(), usuario.getSenha());
    }

    @PostMapping("comentar")
    public void comentar(@RequestBody Comentario comentario){
        comentarioService.comentar(comentario);
    }
    @GetMapping("comentarios")
    public List<Comentario> listarComentariosDoUsuario(@RequestBody String email) {
        return comentarioService.listarComentariosDoUsuario(email);
    }
}
