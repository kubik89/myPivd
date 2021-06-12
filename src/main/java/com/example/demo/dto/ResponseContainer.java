package com.example.demo.dto;

public class ResponseContainer {
    private String errorMessage;
    private String infoMessage;
    private String successMessage;

    public ResponseContainer(String errorMessage, String infoMessage, String successMessage) {
        this.errorMessage = errorMessage;
        this.infoMessage = infoMessage;
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
