package boardcamp.boardcamp.exceptions.CustomerExceptions;

public class CustomerConflictException extends RuntimeException {
  public CustomerConflictException(String message){
    super(message);
  }
}
