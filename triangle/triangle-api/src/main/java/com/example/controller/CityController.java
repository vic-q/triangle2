package com.example.controller;

import com.example.application.CityApplicationService;
import com.example.common.Result;
import com.example.contract.request.GetCityByIdRequest;
import com.example.entity.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wangqing 
 */
@RestController
@RequestMapping(value = "/triangle/api")
public class CityController {

    private CityApplicationService cityApplicationService;

    @Autowired
    public CityController(CityApplicationService cityApplicationService) {
        this.cityApplicationService = cityApplicationService;
    }

    @RequestMapping(value = "city/queryById", method = RequestMethod.POST)
    public Result<City> queryById(@Valid @RequestBody GetCityByIdRequest request, BindingResult result) {
        City city = cityApplicationService.queryById(request);
        return Result.<City>newSuccessResponse().result(city).build();
    }

}
