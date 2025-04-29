package de.hbrs.se2.control.company;

import de.hbrs.se2.control.image.ImageService;
import de.hbrs.se2.model.company.Company;
import de.hbrs.se2.model.company.CompanyRepository;
import de.hbrs.se2.model.user.User;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private ImageService imageService;
    public @Nullable Company addCompany(Company company) { // code von my aus StudentService in angepasster form
        return companyRepository.save(company);
    }
    public @Nullable Company findCompanyByUser(User user) {
        return this.companyRepository.findCompanyByUser(user.getId());
    }
    public List<Company> findAllCompanies() {
        return this.companyRepository.findAll();
    }
    public void deleteCompany(Company company) {
        this.companyRepository.delete(company);
    }

}
