package com.example.ReserveAt.Service;

import com.example.ReserveAt.Dto.BusinessDTO;
import com.example.ReserveAt.Dto.LoginDTO;
import com.example.ReserveAt.Dto.WorkingHoursDTO;
import com.example.ReserveAt.Model.Business;
import com.example.ReserveAt.Model.BusinessType;
import com.example.ReserveAt.Model.City;
import com.example.ReserveAt.Response.LoginMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BusinessService {

    String addBiz(BusinessDTO businessDTO);

    LoginMessage loginBiz(LoginDTO loginDTO);

    BusinessDTO getBizDetailsCard(Long businessId);

    BusinessDTO getBusinessDetails(String email);

    Page<BusinessDTO> findBusinessesByTypeAndCity(City city, BusinessType businessType, Pageable pageable);

    BusinessDTO getBusinessByNameAndCity(String businessName, String city);

    void addWorkingHours(Long businessId, List<WorkingHoursDTO> workingHoursDTOs);

    List<WorkingHoursDTO> getWorkingHours(Long businessId);

    void updateWorkingHours(Long businessId, List<WorkingHoursDTO> workingHoursDTOs);

    void deleteWorkingHours(Long workingHoursId);
}
