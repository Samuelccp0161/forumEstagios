package br.edu.facima.forum.cadastro;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"nome", "email", "matricula", "contato"})
public class Cadastro {

    private String nome;
    private String email;
    private Long matricula;
    private Long contato;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public Long getContato() {
        return contato;
    }

    public void setContato(Long contato) {
        this.contato = contato;
    }

    public boolean cadastroFoiFeito(){
        return nome != null && email != null && matricula != null && contato != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cadastro cadastro = (Cadastro) o;

        if (!nome.equals(cadastro.nome)) return false;
        if (!email.equals(cadastro.email)) return false;
        if (!matricula.equals(cadastro.matricula)) return false;
        return contato.equals(cadastro.contato);
    }

    @Override
    public int hashCode() {
        int result = nome.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + matricula.hashCode();
        result = 31 * result + contato.hashCode();
        return result;
    }
}
