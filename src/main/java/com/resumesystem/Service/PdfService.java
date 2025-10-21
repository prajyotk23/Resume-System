package com.resumesystem.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.resumesystem.DTO.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final TemplateEngine templateEngine;

    public byte[] generateResumePdf(UserDTO userDTO) throws IOException {
        Context context = new Context();
        context.setVariable("name", userDTO.getName());
        context.setVariable("email", userDTO.getEmail());
        context.setVariable("role", userDTO.getRole());
        context.setVariable("projects", userDTO.getProjects());
        context.setVariable("skills", userDTO.getSkills());
        context.setVariable("internships", userDTO.getInternships());
        context.setVariable("certificates", userDTO.getCertificates());

        String html = templateEngine.process("resume", context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(html, null);
        builder.toStream(outputStream);
        builder.run();

        return outputStream.toByteArray();
    }
}


