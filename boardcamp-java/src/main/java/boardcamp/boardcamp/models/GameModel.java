package boardcamp.boardcamp.models;

import boardcamp.boardcamp.dto.GameDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class GameModel {
  
  public GameModel(GameDTO game){
    this.image = game.getImage();
    this.name = game.getName();
    this.pricePerDay = game.getPricePerDay();
    this.stockTotal = game.getStockTotal();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @Column(length = 25, unique = true, nullable = false)
  private String name;

  @Column(nullable = false)
  private String image;

  @Column(nullable = false)
  private int stockTotal;

  @Column(nullable = false)
  private long pricePerDay;
}
