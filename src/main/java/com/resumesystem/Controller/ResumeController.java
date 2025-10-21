package com.resumesystem.Controller;

import com.resumesystem.DTO.ResumeDTO;
import com.resumesystem.Service.ResumeService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @GetMapping
    public ResponseEntity<ResumeDTO> getResume() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(resumeService.getResumeByEmail(email));
    }
    
    @PostMapping("/save")
    public ResponseEntity<?> saveResume(@RequestBody ResumeDTO dto) {
        // Get email from security context (JWT authenticated)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // Save resume
        resumeService.saveResume(dto, email);

        return ResponseEntity.ok(Map.of("message", "Resume saved successfully"));
    }




}

