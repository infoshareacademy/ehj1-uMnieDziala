package com.isa.unasdziala.repository;

import com.isa.unasdziala.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    List<Holiday> findAllByEmployees_Id(Long employeeId);
    Optional<Holiday> findByIdAndEmployees_Id(Long id, Long userId);
}