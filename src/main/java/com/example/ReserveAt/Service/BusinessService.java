package com.example.ReserveAt.Service;

import com.example.ReserveAt.Dto.BusinessDTO;
import com.example.ReserveAt.Dto.LoginDTO;
import com.example.ReserveAt.Response.LoginMessage;

public interface BusinessService {

    String addBiz(BusinessDTO businessDTO);

    LoginMessage loginBiz(LoginDTO loginDTO);

    BusinessDTO getBizDetailsCard(Long businessId);

    BusinessDTO getBusinessDetails(String email);
}
