package tech.hidetora.graalvmnativeimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.hidetora.graalvmnativeimage.entity.AppUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    // find user by email ignoring case
    Optional<AppUser> findByEmailIgnoreCase(String email);
}
