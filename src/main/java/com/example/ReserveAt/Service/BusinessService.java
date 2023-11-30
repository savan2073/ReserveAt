package com.example.ReserveAt.Service;

import com.example.ReserveAt.Dto.CompanyDTO;
import com.example.ReserveAt.Dto.LoginDTO;
import com.example.ReserveAt.Response.LoginMessage;

public interface BusinessService {

    String addBiz(CompanyDTO companyDTO);

    LoginMessage loginBiz(LoginDTO loginDTO);
}
