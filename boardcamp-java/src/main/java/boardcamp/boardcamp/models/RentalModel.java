package boardcamp.boardcamp.models;

import java.time.LocalDate;

import boardcamp.boardcamp.dto.RentalDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalModel {

  public RentalModel(RentalDTO body, CustomerModel customer, GameModel game){
    this.rentDate = LocalDate.now();
    this.daysRented = body.getDaysRented();
    this.returnDate = null;
    this.originalPrice = game.getPricePerDay()*body.getDaysRented();
    this.delayFee = 0;
    this.customer = customer;
    this.game = game;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @ManyToOne
  @JoinColumn(name="customer_id")
  private CustomerModel customer;

  @ManyToOne
  @JoinColumn(name="game_id")
  private GameModel game;

  @Column(nullable = false)
  private LocalDate rentDate;

  @Column(nullable = false)
  private int daysRented;

  @Column
  private LocalDate returnDate;

  @Column(nullable = false)
  private long originalPrice;

  @Column(nullable = false)
  private long delayFee;
}
