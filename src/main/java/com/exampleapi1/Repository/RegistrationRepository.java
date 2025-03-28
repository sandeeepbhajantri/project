package com.exampleapi1.Repository;

import com.exampleapi1.Entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}