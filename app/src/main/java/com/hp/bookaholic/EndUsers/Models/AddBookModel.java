package com.hp.bookaholic.EndUsers.Models;

public class AddBookModel {
    /**
     * status : success
     * error : false
     * message : Inserted successfully
     * url : http://srishti-systems.info/projects/BookLenging_App/uploads/950208-2states.jpg
     */

    private String status;
    private boolean error;
    private String message;
    private String url;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
