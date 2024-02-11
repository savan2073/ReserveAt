package com.example.ReserveAt.Service.Implementation;

import com.example.ReserveAt.Service.MapsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MapsImplementation implements MapsService {

    @Value("${google.maps.apiKey}")
    private String apiKey;
    @Override
    public void geocodeAddress(String address) {

    }
}
