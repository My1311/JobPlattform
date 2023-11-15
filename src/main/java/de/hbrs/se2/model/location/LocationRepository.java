package de.hbrs.se2.model.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;
@EnableJpaRepositories
public interface LocationRepository extends JpaRepository<Location, UUID> {
}
