package boardcamp.boardcamp.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.databind.ObjectMapper;

import boardcamp.boardcamp.dto.CustomerDTO;
import boardcamp.boardcamp.models.CustomerModel;
import boardcamp.boardcamp.repositories.CustomerRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CustomerIntegrationTests {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Autowired
  private CustomerRepository customerRepository;

  @AfterEach
  void cleanDatabase() {
    customerRepository.deleteAll();
  }

  @Test
  void givenExistentCustomer_whenCreatingCustomer_thenThrowsError() {
    CustomerDTO customerDTO = new CustomerDTO("name", "12345678900");
    CustomerModel createdCustomer = new CustomerModel(customerDTO);
    customerRepository.save(createdCustomer);

    HttpEntity<CustomerDTO> body = new HttpEntity<>(customerDTO);

    ResponseEntity<String> response = testRestTemplate.exchange(
        "/customers",
        HttpMethod.POST,
        body,
        String.class);

    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    assertEquals(1, customerRepository.count());
  }

  @Test
  void givenUniqueCustomer_whenCreatingCustomer_thenSendCreatedCustomer() {
    CustomerDTO customerDTO = new CustomerDTO("name", "12345678900");

    HttpEntity<CustomerDTO> body = new HttpEntity<>(customerDTO);

    ResponseEntity<CustomerModel> response = testRestTemplate.exchange(
        "/customers",
        HttpMethod.POST,
        body,
        CustomerModel.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(customerDTO.getName(), response.getBody().getName());
    assertEquals(customerDTO.getCpf(), response.getBody().getCpf());
    assertEquals(1, customerRepository.count());
  }

  @Test
  void givenInvalidCustomerId_whenFindingCustomerById_thenThrowsError() {
    CustomerDTO customerDTO = new CustomerDTO("name", "12345678900");
    CustomerModel customerModel = new CustomerModel(customerDTO);
    CustomerModel createdCustomer = customerRepository.save(customerModel);
    customerRepository.deleteById(createdCustomer.getId());

    ResponseEntity<String> response = testRestTemplate.exchange(
        "/customers/{customerId}",
        HttpMethod.GET,
        null,
        String.class,
        createdCustomer.getId());

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(0, customerRepository.count());
  }

  @Test
  void givenValidCustomerId_whenFindingCustomerById_thenSendsCustomer() {
    CustomerDTO customerDTO = new CustomerDTO("name", "12345678900");
    CustomerModel customerModel = new CustomerModel(customerDTO);
    CustomerModel createdCustomer = customerRepository.save(customerModel);

    ResponseEntity<String> response = testRestTemplate.exchange(
        "/customers/{customerId}",
        HttpMethod.GET,
        null,
        String.class,
        createdCustomer.getId());

        ObjectMapper mapper = new ObjectMapper();
        String jsonCreatedCustomer = "";
        try {
          jsonCreatedCustomer = mapper.writeValueAsString(createdCustomer);
          
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(jsonCreatedCustomer, response.getBody().toString());
  }
}
