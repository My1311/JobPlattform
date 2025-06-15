package de.hbrs.se2.control.advertisement;

import de.hbrs.se2.control.company.CompanyService;
import de.hbrs.se2.model.advertisement.Advertisement;
import de.hbrs.se2.model.advertisement.AdvertisementRepository;
import de.hbrs.se2.model.company.Company;
import de.hbrs.se2.model.company.CompanyRepository;
import de.hbrs.se2.model.user.User;
import de.hbrs.se2.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class AdvertisementService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private CompanyService companyService;


    @Transactional
    public void updateAdvertisement(Advertisement advertisement) throws NoSuchElementException {
        this.advertisementRepository.save(advertisement);
    }

    @Transactional
    public List<Advertisement> getAllAdvertisementOfCompany(String email) {
        return advertisementRepository.getAllOfCompany(email);
    }

    @Transactional
    public List<Advertisement> findAll() {
        return advertisementRepository.findAll();
    }

    /**
     * inserts a new/ updates advertisement into the database
     *
     * @param advertisement the advertisement to be inserted
     */
    @Transactional
    public Advertisement insert(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }


    @Transactional
    public Advertisement findAdvertisementById(UUID id) {
        return advertisementRepository.findAdvertisementById(id);
    }

    @Transactional
    public void delete(Advertisement advertisement) {
        advertisementRepository.delete(advertisement);
    }

}
