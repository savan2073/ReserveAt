package com.example.ReserveAt.Service;

import com.example.ReserveAt.Model.Company;
import com.example.ReserveAt.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    //create new serviceprovider
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    //get all serviceproviders
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    //get serviceprovider by id
    public Company getById(int id) {
        return companyRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
    }

    //update existing service provider
    public Company update(int id, Company companyDetails) {
        Company company = companyRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        company.setCompanyName(companyDetails.getCompanyName());
        company.setDescription(companyDetails.getDescription());
        company.setRating(companyDetails.getRating());
        company.setAddress(companyDetails.getAddress());
        return companyRepository.save(company);
    }

    public void delete(int id){
        Company company = companyRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Service provider not found with id: " + id));
        companyRepository.delete(company);
    }
}
