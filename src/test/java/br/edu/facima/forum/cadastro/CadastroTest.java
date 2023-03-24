package br.edu.facima.forum.cadastro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CadastroTest {
    Cadastro cadastro = new Cadastro();

    @BeforeEach
    void setUp() {
        cadastro.setNome("Maria Jose");
        cadastro.setContato(Long.valueOf("82912345678"));
        cadastro.setEmail("fulano@facima.edu.br");
        cadastro.setMatricula(Long.valueOf("12345678911"));
    }

    @Test
    public void testNomeCadastro(){
        assertEquals("Maria Jose", cadastro.getNome());
    }
    @Test
    public void testContato(){
        assertEquals(Long.valueOf("82912345678"), cadastro.getContato());
    }
    @Test
    public void testEmail(){
        assertEquals("fulano@facima.edu.br", cadastro.getEmail());
    }
    @Test
    public void testMatricula(){
        assertEquals(Long.valueOf("12345678911"), cadastro.getMatricula());
    }
    @Test
    public void testCadastro(){
        assertTrue(cadastro.cadastroFoiFeito());
    }
}
