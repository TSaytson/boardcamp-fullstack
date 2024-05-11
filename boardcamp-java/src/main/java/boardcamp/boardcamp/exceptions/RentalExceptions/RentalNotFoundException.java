package boardcamp.boardcamp.exceptions.RentalExceptions;

public class RentalNotFoundException extends RuntimeException{
  public RentalNotFoundException(String message){
    super(message);
  }
}
