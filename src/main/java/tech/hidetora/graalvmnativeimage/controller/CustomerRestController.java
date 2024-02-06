package tech.hidetora.graalvmnativeimage.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.hidetora.graalvmnativeimage.dto.CustomerDTO;
import tech.hidetora.graalvmnativeimage.service.CustomerService;

import java.util.List;

/**
 * @author hidetora
 * @version 1.0.0
 * @since 2022/04/18
 */
@RestController
@RequestMapping("/api")
public class CustomerRestController {
    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id){
        return customerService.findCustomerById(id);
    }
    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCustomers(@RequestParam String keyword){
        return customerService.searchCustomers(keyword);
    }
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO saveCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        return customerService.saveNewCustomer(customerDTO);
    }
    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(id,customerDTO);
    }
    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }
}
