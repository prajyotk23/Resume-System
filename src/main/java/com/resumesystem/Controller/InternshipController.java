package com.resumesystem.Controller;

import com.resumesystem.Entity.Internship;
import com.resumesystem.Entity.User;
import com.resumesystem.Repository.InternshipRepository;
import com.resumesystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipRepository internshipRepo;
    private final UserRepository userRepo;

    @PostMapping
    public ResponseEntity<Internship> add(@RequestBody Internship internship) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
        internship.setUser(user);
        return ResponseEntity.ok(internshipRepo.save(internship));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Internship> get(@PathVariable Long id) {
        return ResponseEntity.of(internshipRepo.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Internship> update(@PathVariable Long id, @RequestBody Internship updated) {
        Internship existing = internshipRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Internship not found"));

        existing.setCompany(updated.getCompany());
        existing.setRole(updated.getRole());
        existing.setContribution(updated.getContribution());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());

        return ResponseEntity.ok(internshipRepo.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        internshipRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
