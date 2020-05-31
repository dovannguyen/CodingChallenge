package com.guidedchoice.vehicle.exception;

import java.time.Instant;
import java.util.List;

public class ErrorResponse {
	
    private Instant timestamp;
    private int status;
    private String error;
    private List<String> messages;
    private String path;
    
    public ErrorResponse(Instant timestamp,
					    int status,
					    String error,
					    List<String> messages,
					    String path) {

    	this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.path = path;
    }

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
    
}
