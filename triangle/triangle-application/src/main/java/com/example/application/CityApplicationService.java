package com.example.application;

import com.example.biz.service.CityService;
import com.example.contract.request.GetCityByIdRequest;
import com.example.entity.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangqing 
 */
@Service
public class CityApplicationService {

    private CityService cityService;

    @Autowired
    public CityApplicationService(CityService cityService) {
        this.cityService = cityService;
    }

    public City queryById(GetCityByIdRequest request) {
        return cityService.queryById(request);
    }

}
