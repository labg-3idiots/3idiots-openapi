package com.idiots.openapi.repository;

import com.idiots.openapi.entity.UserInterestRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInterestRegionRepository extends JpaRepository<UserInterestRegion, Long> {

    @Query(value =
            "SELECT uir "+
            "FROM UserInterestRegion uir "+
            "GROUP BY uir.region.regionCode"
    )
    List<UserInterestRegion> findAllDistinctRegionCode();

    List<UserInterestRegion> findAllByRegionRegionCode(String regionCode);
}
