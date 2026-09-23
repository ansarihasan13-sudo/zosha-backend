package com.zosha.zosha_backend.controller;

import com.zosha.zosha_backend.entity.Enquiry;
import com.zosha.zosha_backend.service.EnquiryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enquiries")
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "http://localhost:5176"
        }
)
public class EnquiryController {

    private final EnquiryService enquiryService;

    public EnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    // CREATE
    @PostMapping
    public Enquiry createEnquiry(@RequestBody Enquiry enquiry) {
        return enquiryService.createEnquiry(enquiry);
    }

    // GET ALL
    @GetMapping
    public List<Enquiry> getAllEnquiries() {
        return enquiryService.getAllEnquiries();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Enquiry getEnquiryById(@PathVariable Long id) {
        return enquiryService.getEnquiryById(id);
    }

    // MARK AS READ
    @PutMapping("/{id}/read")
    public Enquiry markAsRead(@PathVariable Long id) {
        return enquiryService.markAsRead(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteEnquiry(@PathVariable Long id) {

        enquiryService.deleteEnquiry(id);

        return "Enquiry deleted successfully";
    }
}