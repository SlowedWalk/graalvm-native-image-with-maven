package tech.hidetora.graalvmnativeimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.hidetora.graalvmnativeimage.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query(value = "SELECT " +
            "   customer.id, " +
            "   customer.name, " +
            "   customer.email, " +
            "   customer.image_url, " +
            "   COUNT(invoice.id) AS total_invoices, " +
            "   SUM(CASE WHEN invoice.status = 'pending' THEN invoice.amount ELSE 0 END) AS total_pending, " +
            "   SUM(CASE WHEN invoice.status = 'paid' THEN invoice.amount ELSE 0 END) AS total_paid" +
            " FROM customer" +
            " LEFT JOIN invoice ON customer.id = invoice.customer_id" +
            " WHERE" +
            "  customer.name LIKE :1 OR" +
            "  customer.email LIKE :1" +
            " GROUP BY customer.id, customer.name, customer.email, customer.image_url" +
            " ORDER BY customer.name ASC", nativeQuery = true)
    List<Customer> fetchFilteredCustomers(String query);

    // total pending customer invoices
}
