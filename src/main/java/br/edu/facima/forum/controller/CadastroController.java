package br.edu.facima.forum.controller;

import br.edu.facima.forum.cadastro.Cadastro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastrar")
public class CadastroController {

    @GetMapping
    @ResponseBody
    public String getOk(){
        return "Ok";
    }

    @PostMapping
    public ResponseEntity<Cadastro> salvar(Cadastro cadastro){
        return ResponseEntity.status(HttpStatus.OK).body(cadastro);
    }
}
