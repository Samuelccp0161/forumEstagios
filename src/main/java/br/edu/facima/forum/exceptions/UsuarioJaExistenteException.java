package br.edu.facima.forum.exceptions;

public class UsuarioJaExistenteException extends RuntimeException {
    public UsuarioJaExistenteException(String message) {
        super(message);
    }
}
