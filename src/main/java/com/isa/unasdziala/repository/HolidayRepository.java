package com.isa.unasdziala.repository;

import com.isa.unasdziala.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}