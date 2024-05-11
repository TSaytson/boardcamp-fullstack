package boardcamp.boardcamp.exceptions.RentalExceptions;

public class RentalBadRequestException extends RuntimeException{
  public RentalBadRequestException(String message){
    super(message);
  }
}
