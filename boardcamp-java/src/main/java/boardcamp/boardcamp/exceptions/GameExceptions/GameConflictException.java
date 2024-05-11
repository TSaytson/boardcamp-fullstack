package boardcamp.boardcamp.exceptions.GameExceptions;

public class GameConflictException extends RuntimeException {
  public GameConflictException(String message){
    super(message);
  }
}
