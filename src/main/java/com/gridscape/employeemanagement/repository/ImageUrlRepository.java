package com.gridscape.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gridscape.employeemanagement.dto.ImageUrls;

@Repository
public interface ImageUrlRepository extends JpaRepository<ImageUrls,Long>{

}
