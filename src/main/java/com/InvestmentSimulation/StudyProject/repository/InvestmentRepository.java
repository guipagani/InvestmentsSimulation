package com.InvestmentSimulation.StudyProject.repository;

import com.InvestmentSimulation.StudyProject.model.Investment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}