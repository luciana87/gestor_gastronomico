package org.ada.gestorgastronomico.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ControllerExceptionHandler {

    @ExceptionHandler(ExistingResourceException.class)
    public ResponseEntity handleException(ExistingResourceException e) {

        return new ResponseEntity(ExistingResourceException.MESSAGE,
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleException(ResourceNotFoundException e) {

        return new ResponseEntity(e.getMessage(),
                HttpStatus.NOT_FOUND);
    }
}
