package com.example.biz.service.impl;

import com.example.biz.service.CityService;
import com.example.contract.request.GetCityByIdRequest;
import com.example.entity.City;
import com.example.repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author wangqing
 */
@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City queryById(GetCityByIdRequest request) {
        Optional<City> cityOptional = cityRepository.findById(request.getId());
        if (cityOptional.isPresent()) {
            return cityOptional.get();
        }
        return null;
    }
}
