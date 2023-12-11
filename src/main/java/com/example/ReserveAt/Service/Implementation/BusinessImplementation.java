package com.example.ReserveAt.Service.Implementation;

import com.example.ReserveAt.Dto.BusinessDTO;
import com.example.ReserveAt.Dto.LoginDTO;
import com.example.ReserveAt.Model.Business;
import com.example.ReserveAt.Repository.BusinessRepository;
import com.example.ReserveAt.Repository.ReviewRepository;
import com.example.ReserveAt.Response.LoginMessage;
import com.example.ReserveAt.Service.BusinessService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessImplementation implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addBiz(BusinessDTO businessDTO) {
        Business business = new Business(
                businessDTO.getBusinessId(),
                businessDTO.getBusinessName(),
                businessDTO.getCity(),
                businessDTO.getAddress(),
                businessDTO.getRating(),
                businessDTO.getDescription(),
                businessDTO.getEmployees(),
                businessDTO.getBusinessType(),
                businessDTO.getEmail(),
                this.passwordEncoder.encode(businessDTO.getPassword()),
                businessDTO.getReviews(),
                businessDTO.getPhotoPath()
        );
        businessRepository.save(business);
        return business.getBusinessName() + business.getBusinessType();
    }

    @Override
    public LoginMessage loginBiz(LoginDTO loginDTO) {
        String msg = "";
        Business business = businessRepository.findByEmail(loginDTO.getEmail());
        if (business != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = business.getPassword();
            Boolean isPasswordRight = passwordEncoder.matches(password, encodedPassword);
            if (isPasswordRight) {
                Optional<Business> company1 = businessRepository.findBizByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
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

    @Override
    public BusinessDTO getBizDetailsCard(Long businessId) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new EntityNotFoundException("Business not found."));
        int reviewCount = reviewRepository.countByBusinessBusinessId(businessId);

        double rating = business.getRating();

        return new BusinessDTO(
                business.getBusinessId(),
                business.getBusinessName(),
                business.getCity(),
                null,
                rating,
                null,
                null,
                null,
                null,
                null,
                null,
                business.getPhotoPath(),
                reviewCount
        );
    }
}
