package boardcamp.boardcamp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boardcamp.boardcamp.dto.RentalDTO;
import boardcamp.boardcamp.models.RentalModel;
import boardcamp.boardcamp.services.RentalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rentals")
public class RentalController {
  final RentalService rentalService;

  RentalController(RentalService rentalService){
    this.rentalService = rentalService;
  }

  @GetMapping
  public ResponseEntity<List<RentalModel>> getRentals(){
    return ResponseEntity.status(HttpStatus.OK).body(rentalService.getRentals());
  }

  @PostMapping
  public ResponseEntity<RentalModel> createRental(@RequestBody @Valid RentalDTO body){
    return ResponseEntity.status(HttpStatus.CREATED).body(rentalService.create(body));
  }

  @PutMapping("/{rentalId}/return")
  public ResponseEntity<RentalModel> returnRental(@PathVariable Long rentalId){
    return ResponseEntity.status(HttpStatus.OK).body(rentalService.update(rentalId));
  }
}
