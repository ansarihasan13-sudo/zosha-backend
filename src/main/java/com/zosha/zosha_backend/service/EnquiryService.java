package com.zosha.zosha_backend.service;

import com.zosha.zosha_backend.entity.Enquiry;
import com.zosha.zosha_backend.repository.EnquiryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;

    public EnquiryService(EnquiryRepository enquiryRepository) {
        this.enquiryRepository = enquiryRepository;
    }

    // CREATE
    public Enquiry createEnquiry(Enquiry enquiry) {

        enquiry.setCreatedAt(LocalDateTime.now());
        enquiry.setRead(false);

        return enquiryRepository.save(enquiry);
    }

    // GET ALL
    public List<Enquiry> getAllEnquiries() {
        return enquiryRepository.findAll();
    }

    // GET BY ID
    public Enquiry getEnquiryById(Long id) {
        return enquiryRepository.findById(id).orElse(null);
    }

    // MARK AS READ
    public Enquiry markAsRead(Long id) {

        Enquiry enquiry = enquiryRepository
                .findById(id)
                .orElse(null);

        if (enquiry != null) {
            enquiry.setRead(true);
            return enquiryRepository.save(enquiry);
        }

        return null;
    }

    // DELETE
    public void deleteEnquiry(Long id) {
        enquiryRepository.deleteById(id);
    }
}
