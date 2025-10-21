package com.resumesystem.Controller;

import com.resumesystem.Entity.Certificate;
import com.resumesystem.Entity.User;
import com.resumesystem.Repository.CertificateRepository;
import com.resumesystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateRepository certificateRepo;
    private final UserRepository userRepo;

    @PostMapping
    public ResponseEntity<Certificate> add(@RequestBody Certificate certificate) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
        certificate.setUser(user);
        return ResponseEntity.ok(certificateRepo.save(certificate));
    }



    @GetMapping("/{id}")
    public ResponseEntity<Certificate> get(@PathVariable Long id) {
        return ResponseEntity.of(certificateRepo.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificate> update(@PathVariable Long id, @RequestBody Certificate updated) {
        Certificate existing = certificateRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Certificate not found"));

        existing.setCourseName(updated.getCourseName());
        existing.setPlatform(updated.getPlatform());
        existing.setCertificateLink(updated.getCertificateLink());
        existing.setCompletionDate(updated.getCompletionDate());

        return ResponseEntity.ok(certificateRepo.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        certificateRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

