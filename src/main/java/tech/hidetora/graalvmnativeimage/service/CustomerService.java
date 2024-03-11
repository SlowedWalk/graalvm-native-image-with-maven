package tech.hidetora.graalvmnativeimage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.hidetora.graalvmnativeimage.entity.Customer;
import tech.hidetora.graalvmnativeimage.repository.CustomerRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Map getCustomersFiltered(String query) {
        Map<String, Object> map = Map.of("total_pending", null, "total_paid", null);
        customerRepository.fetchFilteredCustomers(query).forEach(customer -> {
            customer.getInvoices().forEach(invoice -> {
                if (invoice.getStatus().equals("pending")) {
                    map.put("total_pending", (Double) map.get("total_pending") + invoice.getAmount());
                } else {
                    map.put("total_paid", (Double) map.get("total_paid") + invoice.getAmount());
                }
            });
        });
        return map;
    }
}
