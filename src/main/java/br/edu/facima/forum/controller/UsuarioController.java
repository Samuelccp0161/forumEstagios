package br.edu.facima.forum.controller;

import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/cadastrar")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/api/cadastrar")
    public void cadastrar(@RequestBody Usuario usuario){
        usuarioService.cadastrar(usuario);
    }
    @PostMapping("/api/login")
    public void logar(String email, String senha){
        usuarioService.logar(email,senha);
    }
}
