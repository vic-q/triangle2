package com.example.repository;

import com.example.entity.City;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangqing
 */

public interface CityRepository extends JpaRepository<City, Integer> {
}
