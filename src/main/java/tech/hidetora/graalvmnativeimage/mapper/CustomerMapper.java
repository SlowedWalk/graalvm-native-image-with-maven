package tech.hidetora.graalvmnativeimage.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.hidetora.graalvmnativeimage.dto.CustomerDTO;
import tech.hidetora.graalvmnativeimage.entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hidetora
 * @version 1.0.0
 * @since 2022/04/18
 */

@Service
public class CustomerMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public CustomerDTO fromCustomer(Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, Customer.class);
    }
    public List<CustomerDTO> fromListCustomers(List<Customer> customers){
        return customers.stream().map(c->modelMapper.map(c, CustomerDTO.class)).collect(Collectors.toList());
    }
}
