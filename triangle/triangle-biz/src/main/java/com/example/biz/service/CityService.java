package com.example.biz.service;

import com.example.contract.request.GetCityByIdRequest;
import com.example.entity.City;

/**
 * @author wangqing
 */
public interface CityService {

    City queryById(GetCityByIdRequest request);
}
