package com.resumesystem.Controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resumesystem.DTO.UserDTO;
import com.resumesystem.Entity.User;
import com.resumesystem.Repository.UserRepository;
import com.resumesystem.Service.PdfService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class PdfController {

    private final PdfService pdfService;
    private final UserRepository userRepository;

    @GetMapping("/resume")
    public ResponseEntity<byte[]> getResumePdf() throws IOException {
        // ✅ Get authenticated user's email from JWT
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
        String email = auth.getName();

        // ✅ Fetch user entity
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Convert to DTO for PDF generation
        UserDTO dto = UserDTO.from(user);

        // ✅ Generate PDF bytes
        byte[] pdfBytes = pdfService.generateResumePdf(dto);

        // ✅ Return as downloadable PDF
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Resume.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
