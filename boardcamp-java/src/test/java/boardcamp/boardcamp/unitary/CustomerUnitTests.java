package boardcamp.boardcamp.unitary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import boardcamp.boardcamp.dto.CustomerDTO;
import boardcamp.boardcamp.exceptions.CustomerExceptions.CustomerConflictException;
import boardcamp.boardcamp.exceptions.CustomerExceptions.CustomerNotFoundException;
import boardcamp.boardcamp.models.CustomerModel;
import boardcamp.boardcamp.repositories.CustomerRepository;
import boardcamp.boardcamp.services.CustomerService;

@SpringBootTest
public class CustomerUnitTests {
  @InjectMocks
  private CustomerService customerService;

  @Mock
  private CustomerRepository customerRepository;

  @Test
  void givenRepeatedCustomer_whenCreatingCustomer_thenThrowsError(){
    CustomerDTO customer = new CustomerDTO();
    doReturn(true).when(customerRepository).existsByCpf(any());

    CustomerConflictException exception = assertThrows(
      CustomerConflictException.class, () -> customerService.create(customer)
      );

    verify(customerRepository, times(1)).existsByCpf(any());
    verify(customerRepository, times(0)).save(any());
    assertNotNull(exception);
    assertEquals("Customer already exists", exception.getMessage());
  }

  @Test
  void givenUniqueCustomer_whenCreatingCustomer_thenReturnCreatedCustomer(){
    CustomerDTO customerDTO = new CustomerDTO("name", "cpf");
    CustomerModel customerModel = new CustomerModel(customerDTO);
    doReturn(false).when(customerRepository).existsByCpf(any());
    doReturn(customerModel).when(customerRepository).save(any());

    CustomerModel newCustomer = customerService.create(customerDTO);

    assertNotNull(newCustomer);
    verify(customerRepository, times(1)).existsByCpf(any());
    verify(customerRepository, times(1)).save(any());
    assertEquals(customerModel, newCustomer);
  }

  @Test
  void givenInvalidCustomerId_whenFindingCustomerById_thenThrowsError(){
    doReturn(false).when(customerRepository).existsById(any());

    CustomerNotFoundException exception = assertThrows(
      CustomerNotFoundException.class, () -> customerService.getCustomerById(any())
      );

    verify(customerRepository, times(1)).findById(any());
    assertNotNull(exception);
    assertEquals("Customer does not exists", exception.getMessage());
  }
  @Test
  void givenValidCustomerId_whenFindingCustomerById_thenReturnsCustomer(){
    CustomerDTO customer = new CustomerDTO();
    CustomerModel newCustomer = new CustomerModel(customer);

    doReturn(Optional.of(newCustomer)).when(customerRepository).findById(any());

    CustomerModel result = customerService.getCustomerById(newCustomer.getId());
    verify(customerRepository, times(1)).findById(any());
    assertEquals(result, newCustomer);
  }

}
