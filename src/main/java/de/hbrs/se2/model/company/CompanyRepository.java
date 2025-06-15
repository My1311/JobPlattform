package de.hbrs.se2.model.company;

import io.micrometer.core.lang.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@EnableJpaRepositories
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    @Query("SELECT company FROM Company company WHERE company.user.id=:userId")
    @Nullable
    Company findCompanyByUser(@Param("userId") UUID userId);

    @Query("SELECT company.logo FROM Company company WHERE company.id=:id")
    byte[] getLogoByCompany(@Param("id") UUID id);
}