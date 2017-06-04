package br.unisinos.sistemapdv.infrastructure.dto;

/**
 * Created by lfaitao on 5/14/17.
 */
public class FeedbackDTO {

    private boolean status;
    private String message;

    public FeedbackDTO(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
