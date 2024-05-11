package boardcamp.boardcamp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import boardcamp.boardcamp.exceptions.GameExceptions.GameBadRequestException;
import boardcamp.boardcamp.exceptions.GameExceptions.GameConflictException;
import boardcamp.boardcamp.exceptions.GameExceptions.GameNotFoundException;

@ControllerAdvice
public class GameExceptionHandler {
  
  @ExceptionHandler({GameBadRequestException.class})
  public ResponseEntity<String> handleGameBadRequest(GameBadRequestException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler({GameConflictException.class})
  public ResponseEntity<String> handleGameConflict(GameConflictException e){
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

  @ExceptionHandler({GameNotFoundException.class})
  public ResponseEntity<String> handleGameNotFound(GameNotFoundException e){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }
}
