package com.zosha.zosha_backend.controller;

import com.zosha.zosha_backend.dto.LoginRequestDTO;
import com.zosha.zosha_backend.dto.LoginResponseDTO;
import com.zosha.zosha_backend.entity.Admin;
import com.zosha.zosha_backend.service.AdminService;
import com.zosha.zosha_backend.service.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private final AdminService adminService;
    private final JwtService jwtService;

    public AdminController(AdminService adminService,
                           JwtService jwtService) {
        this.adminService = adminService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {

        Admin admin = adminService.login(
                dto.getUsername(),
                dto.getPassword()
        );

        if (admin != null) {

            String token = jwtService.generateToken(
                    admin.getUsername()
            );

            return new LoginResponseDTO(
                    "Login successful",
                    admin.getUsername(),
                    token
            );
        }

        return new LoginResponseDTO(
                "Invalid username or password",
                null,
                null
        );
    }
}