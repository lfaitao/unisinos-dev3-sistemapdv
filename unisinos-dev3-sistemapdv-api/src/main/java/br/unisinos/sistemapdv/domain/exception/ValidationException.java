package br.unisinos.sistemapdv.domain.exception;

import br.unisinos.sistemapdv.infrastructure.dto.FeedbackDTO;

/**
 * Created by lfaitao on 5/31/17.
 */
public class ValidationException extends Exception {

    private static final boolean STATUS_FALHA = false;

    /*
     * Atributos
     */
    private String mensagem;
    private boolean status;

    /*
     * Construtor
     */
    public ValidationException(String mensagem) {
        this.mensagem = mensagem;
        this.status = STATUS_FALHA;
    }

    /*
     * Getters
     */
    public FeedbackDTO getFeedback() {
        return new FeedbackDTO(this.status, this.mensagem);
    }

}
