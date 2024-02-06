package tech.hidetora.graalvmnativeimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.hidetora.graalvmnativeimage.entity.Customer;

import java.util.List;
import java.util.Optional;

/**
 * @author hidetora
 * @version 1.0.0
 * @since 2022/04/18
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstNameContainingIgnoreCase(String keyword);
    Optional<Customer> findByEmail(String email);
}
