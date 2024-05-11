package boardcamp.boardcamp.controllers;

import org.springframework.web.bind.annotation.RestController;

import boardcamp.boardcamp.dto.CustomerDTO;
import boardcamp.boardcamp.models.CustomerModel;
import boardcamp.boardcamp.services.CustomerService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customers")
public class CustomerController {
  final CustomerService customerService;

  CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerModel> getCustomerById(@PathVariable Long customerId) {
    CustomerModel customer = customerService.getCustomerById(customerId);
    return ResponseEntity.ok().body(customer);
  }

  @PostMapping
  public ResponseEntity<CustomerModel> createCustomer(@RequestBody @Valid CustomerDTO body) {
    return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(body));
  }

}
