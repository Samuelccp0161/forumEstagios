package br.edu.facima.forum.controller;

import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastrar")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public void cadastrar(@RequestBody Usuario usuario){
        usuarioService.cadastrar(usuario);
    }
}
