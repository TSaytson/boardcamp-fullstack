package boardcamp.boardcamp.services;

import org.springframework.stereotype.Service;

import boardcamp.boardcamp.dto.CustomerDTO;
import boardcamp.boardcamp.exceptions.CustomerExceptions.CustomerConflictException;
import boardcamp.boardcamp.exceptions.CustomerExceptions.CustomerNotFoundException;
import boardcamp.boardcamp.models.CustomerModel;
import boardcamp.boardcamp.repositories.CustomerRepository;

@Service
public class CustomerService {
  final CustomerRepository customerRepository;

  CustomerService(CustomerRepository customerRepository){
    this.customerRepository = customerRepository;
  }

  public CustomerModel create(CustomerDTO body) throws CustomerConflictException{
    if (customerRepository.existsByCpf(body.getCpf()))
      throw new CustomerConflictException("Customer already exists");
    
    CustomerModel customer = new CustomerModel(body);
    return customerRepository.save(customer);
  }

  public CustomerModel getCustomerById(Long id) throws CustomerNotFoundException{
    CustomerModel customer = customerRepository.findById(id).orElseThrow(
      () -> new CustomerNotFoundException("Customer does not exists")
    );

    return customer;
  }
}
