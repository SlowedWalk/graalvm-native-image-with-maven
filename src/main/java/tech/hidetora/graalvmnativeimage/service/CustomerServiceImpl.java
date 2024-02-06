package tech.hidetora.graalvmnativeimage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.hidetora.graalvmnativeimage.dto.CustomerDTO;
import tech.hidetora.graalvmnativeimage.entity.Customer;
import tech.hidetora.graalvmnativeimage.exception.CustomerNotFoundException;
import tech.hidetora.graalvmnativeimage.exception.EmailAlreadyExistException;
import tech.hidetora.graalvmnativeimage.mapper.CustomerMapper;
import tech.hidetora.graalvmnativeimage.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author hidetora
 * @version 1.0.0
 * @since 2022/04/18
 */

@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) throws EmailAlreadyExistException {
        log.info(String.format("Saving new Customer => %s ", customerDTO.toString()));
        Optional<Customer> byEmail = customerRepository.findByEmail(customerDTO.getEmail());
        if(byEmail.isPresent()) {
            log.error("This email {} already exist", customerDTO.getEmail());
            throw new EmailAlreadyExistException("This email already exist!");
        }
        Customer customerToSave = customerMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customerToSave);
        return customerMapper.fromCustomer(savedCustomer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return customerMapper.fromListCustomers(allCustomers);
    }

    @Override
    public CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers = customerRepository.findByFirstNameContainingIgnoreCase(keyword);
        return customerMapper.fromListCustomers(customers);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
        CustomerDTO newCustomerDTO = CustomerDTO.builder()
                .id(id)
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .email(customerDTO.getEmail())
                .build();
        Customer customerToUpdate = customerMapper.fromCustomerDTO(customerDTO);
        Customer updatedCustomer = customerRepository.save(customerToUpdate);
        return customerMapper.fromCustomer(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
        customerRepository.deleteById(id);
    }
}
