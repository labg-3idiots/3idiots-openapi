package com.idiots.openapi.repository;

import com.idiots.openapi.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, String> {
    @Query("SELECT r FROM Region r")
    List<Region> getRegionTest();
    List<Region> getRegionByFirstRegion(String firstRegion);
}
