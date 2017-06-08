package br.unisinos.sistemapdv.infrastructure.dto;

/**
 * Created by lfaitao on 5/14/17.
 */
public class FeedbackDTO {

    private boolean status;
    private String message;
    private Object object;

    public FeedbackDTO(boolean status, String message) {
        this(status, message, null);
    }

    public FeedbackDTO(boolean status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
