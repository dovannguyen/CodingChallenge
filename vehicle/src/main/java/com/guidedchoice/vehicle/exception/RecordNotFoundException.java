package com.guidedchoice.vehicle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String exceptionDetail;
    private Object fieldValue;

    public RecordNotFoundException( String exceptionDetail, Integer id) {
        super(exceptionDetail+" - "+id);
        this.exceptionDetail = exceptionDetail;
        this.fieldValue = id;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}