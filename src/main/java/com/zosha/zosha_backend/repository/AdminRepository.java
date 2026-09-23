package com.zosha.zosha_backend.repository;
import java.util.Optional;
import com.zosha.zosha_backend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByUsername(String username);
}
