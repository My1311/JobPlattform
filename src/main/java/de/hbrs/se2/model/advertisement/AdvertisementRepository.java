package de.hbrs.se2.model.advertisement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

@EnableJpaRepositories
public interface AdvertisementRepository extends JpaRepository<Advertisement, UUID> {


    @Query("select advertisement from Advertisement advertisement "
            + "where advertisement.id=:advertisementId")
    Advertisement findAdvertisementById(@Param("advertisementId") UUID advertisementId);



    @Query("SELECT a FROM Advertisement a JOIN a.company c WHERE c.user.email = :email")
    List<Advertisement> getAllOfCompany(@Param("email") String email);



}
