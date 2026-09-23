package com.zosha.zosha_backend.service;

import com.zosha.zosha_backend.entity.Admin;
import com.zosha.zosha_backend.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository,
                        PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin createAdmin(Admin admin) {

        String encodedPassword =
                passwordEncoder.encode(admin.getPassword());

        admin.setPassword(encodedPassword);

        return adminRepository.save(admin);
    }

    public Admin login(String username, String password) {

        Admin admin =
                adminRepository.findByUsername(username).orElse(null);

        if (admin != null &&
                passwordEncoder.matches(password, admin.getPassword())) {

            return admin;
        }

        return null;
    }
}