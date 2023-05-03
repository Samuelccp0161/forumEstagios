package br.edu.facima.forum.controller;

import br.edu.facima.forum.model.Usuario;
import br.edu.facima.forum.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastrar")
public class CadastroController {

    private final UsuarioService usuarioService;

    public CadastroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping
    public void cadastrar(@RequestBody Usuario usuario){
        usuarioService.cadastrar(usuario);
    }
}
