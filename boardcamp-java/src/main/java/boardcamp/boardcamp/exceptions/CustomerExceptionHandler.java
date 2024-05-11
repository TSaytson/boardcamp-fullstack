package boardcamp.boardcamp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import boardcamp.boardcamp.exceptions.CustomerExceptions.CustomerConflictException;
import boardcamp.boardcamp.exceptions.CustomerExceptions.CustomerNotFoundException;

@ControllerAdvice
public class CustomerExceptionHandler {

  @ExceptionHandler({CustomerNotFoundException.class})
  public ResponseEntity<String> handleCustomerNotFound(CustomerNotFoundException e){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler({CustomerConflictException.class})
  public ResponseEntity<String> handleCustomerConflictException(CustomerConflictException e){
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }
}
