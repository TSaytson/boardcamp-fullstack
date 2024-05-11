package boardcamp.boardcamp.exceptions.CustomerExceptions;

public class CustomerNotFoundException extends RuntimeException{
  public CustomerNotFoundException(String message){
    super(message);
  }
}
