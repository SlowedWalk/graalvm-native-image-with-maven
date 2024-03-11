package tech.hidetora.graalvmnativeimage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.hidetora.graalvmnativeimage.entity.Customer;
import tech.hidetora.graalvmnativeimage.entity.Invoice;
import tech.hidetora.graalvmnativeimage.entity.Revenue;
import tech.hidetora.graalvmnativeimage.repository.CustomerRepository;
import tech.hidetora.graalvmnativeimage.repository.InvoiceRepository;
import tech.hidetora.graalvmnativeimage.repository.RevenueRepository;
import tech.hidetora.graalvmnativeimage.service.CustomerService;

import java.util.*;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
class Controller {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final RevenueRepository revenueRepository;
    private final InvoiceRepository invoiceRepository;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/filtered")
    public Map getCustomersFiltered(@RequestParam String query) {
        return customerService.getCustomersFiltered(query);
    }

    @GetMapping("/revenue")
    public List<Revenue> getRevenues() {
        return revenueRepository.findAll();
    }

    @GetMapping("/invoices")
    public List<Invoice> getLatestInvoices() {
        return invoiceRepository.getLatestInvoices();
    }
    @GetMapping("/invoices:{id}")
    public Invoice getInvoice(@PathVariable Long id) {
        return invoiceRepository.findById(id).get();
    }

    @GetMapping("/card-data")
    public Map<String, ?> getCardData() {
         return Map.of(
                 "numberOfInvoices", invoiceRepository.count(),
                 "numberOfCustomers", customerRepository.count(),
                 "totalPaidInvoices", invoiceRepository.totalPaidInvoices(),
                 "totalPendingInvoices", invoiceRepository.totalPendingInvoices()
         );
    }

    @GetMapping("/")
    public String welcome() {
        return "Welcome to GraalVM Native Image";
    }
}
