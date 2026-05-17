package com.duoc.repository;


import com.duoc.model.RegionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<RegionModel, Integer> {


}
