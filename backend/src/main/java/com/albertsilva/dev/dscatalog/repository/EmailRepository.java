package com.albertsilva.dev.dscatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertsilva.dev.dscatalog.domain.recovery.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
  
}
