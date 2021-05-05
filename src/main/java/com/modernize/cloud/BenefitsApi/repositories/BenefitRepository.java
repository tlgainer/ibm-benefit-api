package com.modernize.cloud.BenefitsApi.repositories;

import com.modernize.cloud.BenefitsApi.model.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitRepository extends JpaRepository<Benefit, Long> {
}
