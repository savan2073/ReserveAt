package com.example.ReserveAt.Controller;

import com.example.ReserveAt.Model.BusinessType;
import com.example.ReserveAt.Model.City;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class EnumController {
    @GetMapping("/cities")
    public City[] getCities() {
        return City.values();
    }

    @GetMapping("/businessTypes")
    public BusinessType[] getBusinessTypes() {
        return BusinessType.values();
    }
}
