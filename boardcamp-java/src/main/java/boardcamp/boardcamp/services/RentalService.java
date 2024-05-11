package boardcamp.boardcamp.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import boardcamp.boardcamp.dto.RentalDTO;
import boardcamp.boardcamp.exceptions.RentalExceptions.RentalNotFoundException;
import boardcamp.boardcamp.exceptions.RentalExceptions.RentalUnprocessableEntityException;
import boardcamp.boardcamp.models.CustomerModel;
import boardcamp.boardcamp.models.GameModel;
import boardcamp.boardcamp.models.RentalModel;
import boardcamp.boardcamp.repositories.RentalRepository;

@Service
public class RentalService {
  final RentalRepository rentalRepository;
  final GameService gameService;
  final CustomerService customerService;

  RentalService(RentalRepository rentalRepository, 
  GameService gameService,
  CustomerService customerService){
    this.rentalRepository = rentalRepository;
    this.gameService = gameService;
    this.customerService = customerService;
  }

  public RentalModel create(RentalDTO body){
    CustomerModel customer = customerService.getCustomerById(body.getCustomerId());
    GameModel game = gameService.getGameById(body.getGameId());
    List<RentalModel> UnfinishedRentalsByGame = 
      rentalRepository.findUnfinishedRentalsByGameId(body.getGameId());
    if (UnfinishedRentalsByGame.size() >= game.getStockTotal())
      throw new RentalUnprocessableEntityException("This game has ran out of stock");
    RentalModel rental = new RentalModel(body, customer, game);
    return rentalRepository.save(rental);
  }

  public List<RentalModel> getRentals(){
    return rentalRepository.findAll();
  }

  public RentalModel update(Long id){
    RentalModel rental = rentalRepository.findById(id).orElseThrow(
      () -> new RentalNotFoundException("Rental does not exists")
    );
    if (rental.getReturnDate() != null)
      throw new RentalUnprocessableEntityException("Rental has already been finalized");
    else
      rental.setReturnDate(LocalDate.now());
    LocalDate dateToBeReturned = rental.getRentDate().plusDays(rental.getDaysRented());
    if (rental.getReturnDate().isAfter(dateToBeReturned)){
      long daysDelayed = rental.getReturnDate().getDayOfYear() - dateToBeReturned.getDayOfYear();
      rental.setDelayFee(daysDelayed*rental.getGame().getPricePerDay());
    }
    return rentalRepository.save(rental);
  }
}
