package tech.hidetora.graalvmnativeimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.hidetora.graalvmnativeimage.entity.Revenue;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
}
