package boardcamp.boardcamp.exceptions.GameExceptions;

public class GameBadRequestException extends RuntimeException{
  public GameBadRequestException(String message){
    super(message);
  }
}
