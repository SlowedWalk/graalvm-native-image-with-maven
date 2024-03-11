package tech.hidetora.graalvmnativeimage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.hidetora.graalvmnativeimage.entity.Invoice;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    // get latest invoices
    @Query(value = "SELECT invoice.amount, invoice.date, customer.image_url, customer.email, invoice.id" +
            "      FROM invoice" +
            "      JOIN customer ON invoice.customer_id = customer.id" +
            "      ORDER BY invoice.date DESC" +
            "      LIMIT 5", nativeQuery = true)
    List<Invoice> getLatestInvoices();

    // totalPaidInvoices
    @Query(value = "SELECT COUNT(*) FROM invoice WHERE status = 'paid'", nativeQuery = true)
    int totalPaidInvoices();

    // totalPendingInvoices
    @Query(value = "SELECT COUNT(*) FROM invoice WHERE status = 'pending'", nativeQuery = true)
    int totalPendingInvoices();

    // fetch Filtered Invoices
    @Query(value = "SELECT invoice.id, invoice.amount, invoice.date, invoice.status, customer.name, customer.image_url, customer.email" +
            "      FROM invoice" +
            "      JOIN customer ON invoice.customer_id = customer.id" +
            "      WHERE " +
            "           customer.name LIKE ?1 OR " +
            "           customer.email LIKE ?1 OR " +
            "           invoice.amount LIKE ?1 OR " +
            "           invoice.date LIKE ?1 OR " +
            "           invoice.status LIKE ?1 " +
            "       ", nativeQuery = true)
    Page<Invoice> fetchFilteredInvoices(String query, Pageable pageable);

    // fetch Invoices Pages
/*    @Query(value = "SELECT COUNT(*)" +
            "    FROM invoice" +
            "    JOIN customer ON invoice.customer_id = customer.id" +
            "    WHERE" +
            "      customer.name LIKE ?1 OR" +
            "      customer.email LIKE ?1 OR" +
            "      invoice.amount LIKE ?1 OR" +
            "      invoice.date LIKE ?1 OR" +
            "      invoice.status LIKE ?1", nativeQuery = true)
    Page<Invoice> fetchInvoicesPages(String query, Pageable pageable);*/

}
