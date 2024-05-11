package boardcamp.boardcamp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RentalDTO {

  @NotNull(message = "Rental must be associated to a customer")
  @Positive
  private long customerId;

  @NotNull(message = "Rental must have a game associated with")
  @Positive
  private long gameId;

  @NotNull(message = "Rental must have days rented")
  @Positive
  private int daysRented;
}
