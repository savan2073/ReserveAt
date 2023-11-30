package com.example.ReserveAt.Service.Implementation;

import com.example.ReserveAt.Dto.CompanyDTO;
import com.example.ReserveAt.Dto.LoginDTO;
import com.example.ReserveAt.Model.Company;
import com.example.ReserveAt.Repository.CompanyRepository;
import com.example.ReserveAt.Response.LoginMessage;
import com.example.ReserveAt.Service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessImplementation implements BusinessService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addBiz(CompanyDTO companyDTO) {
        Company company = new Company(
                companyDTO.getCompanyId(),
                companyDTO.getCompanyName(),
                companyDTO.getCity(),
                companyDTO.getAddress(),
                companyDTO.getRating(),
                companyDTO.getDescription(),
                companyDTO.getEmployees(),
                companyDTO.getBusinessType(),
                companyDTO.getEmail(),
                this.passwordEncoder.encode(companyDTO.getPassword()),
                companyDTO.getReviews(),
                companyDTO.getPhotoPath()
        );
        companyRepository.save(company);
        return company.getCompanyName() + company.getBusinessType();
    }

    @Override
    public LoginMessage loginBiz(LoginDTO loginDTO) {
        String msg = "";
        Company company = companyRepository.findByEmail(loginDTO.getEmail());
        if (company != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = company.getPassword();
            Boolean isPasswordRight = passwordEncoder.matches(password, encodedPassword);
            if (isPasswordRight) {
                Optional<Company> company1 = companyRepository.findBizByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (company1.isPresent()) {
                    return new LoginMessage("Login was successfull", true);
                } else {
                    return new LoginMessage("Login failed", false);
                }
            } else {
                return new LoginMessage("Password is wrong", false);
            }
        } else {
            return new LoginMessage("Email does not exist", false);
        }
    }
}
