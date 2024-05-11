package boardcamp.boardcamp.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GameDTO {
  
  @NotBlank(message = "Game must have a name")
  private String name;

  @NotBlank(message = "Game must have an image")
  @URL(message = "Image should be an URL")
  private String image;

  @NotNull(message = "Game must have at least one in stock")
  @Positive(message = "Total game stock must be positive")
  private int stockTotal;

  @NotNull(message = "Game must have a price per day")
  @Positive(message = "Price per day must be positive")
  private long pricePerDay;
}
