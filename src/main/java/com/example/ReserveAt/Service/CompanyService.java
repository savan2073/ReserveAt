package com.example.ReserveAt.Service;

import com.example.ReserveAt.Model.Company;
import com.example.ReserveAt.Model.Employee;
import com.example.ReserveAt.Repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    //create new serviceprovider
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    //get all company
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    //get company by id
    public Company getById(int id) {
        return companyRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
    }

    //update existing company
    public Company update(int id, Company companyDetails) {
        Company company = companyRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        company.setCompanyName(companyDetails.getCompanyName());
        company.setDescription(companyDetails.getDescription());
        company.setRating(companyDetails.getRating());
        company.setAddress(companyDetails.getAddress());
        return companyRepository.save(company);
    }
    //delete company
    public void delete(int id){
        Company company = companyRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        companyRepository.delete(company);
    }
    //get company all employees
    public List<Employee> getAllEmployeesByCompanyId(int id) {
        Optional<Company> company = companyRepository.findById((long) id);
        if (company.isPresent()) {
            return company.get().getEmployees();
        } else {
            //nie znaleziono firmy
            //throw wyjątkiem lub return empty list
            throw new EntityNotFoundException("Company with id " + id + " not found");
        }
    }
}
