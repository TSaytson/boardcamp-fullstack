package boardcamp.boardcamp.models;

import boardcamp.boardcamp.dto.CustomerDTO;
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
@Table(name = "customers")
public class CustomerModel {

  public CustomerModel(CustomerDTO customer){
    this.cpf = customer.getCpf();
    this.name = customer.getName();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @Column(length = 50, nullable = false)
  private String name;

  @Column(length = 11, nullable = false, unique = true)
  private String cpf;
  
}
