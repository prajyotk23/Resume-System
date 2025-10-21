package com.resumesystem.Service;

import com.resumesystem.DTO.*;
import com.resumesystem.Entity.*;
import com.resumesystem.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final ProjectRepository projectRepository;
    private final SkillRepository skillRepository;
    private final InternshipRepository internshipRepository;
    private final CertificateRepository certificateRepository;

    public void saveResume(ResumeDTO dto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Clear old structured entries first
        projectRepository.deleteByUser(user);
        skillRepository.deleteByUser(user);
        internshipRepository.deleteByUser(user);
        certificateRepository.deleteByUser(user);

        // Save structured Projects
        List<Project> projects = dto.getProjects().stream()
                .map(p -> Project.builder()
                        .title(p.getTitle())
                        .description(p.getDescription())
                        .link(p.getLink())
                        .techStack(p.getTechStack())
                        .user(user)
                        .build())
                .collect(Collectors.toList());
        projectRepository.saveAll(projects);

        // Save structured Skills
        List<Skill> skills = dto.getSkills().stream()
                .map(s -> Skill.builder()
                        .skillName(s.getSkillName())
                        .proficiencyLevel(s.getProficiencyLevel())
                        .user(user)
                        .build())
                .collect(Collectors.toList());
        skillRepository.saveAll(skills);

        // Save structured Internships
        List<Internship> internships = dto.getInternships().stream()
                .map(i -> Internship.builder()
                        .role(i.getRole())
                        .company(i.getCompany())
                        .startDate(i.getStartDate())
                        .endDate(i.getEndDate())
                        .contribution(i.getContribution())
                        .user(user)
                        .build())
                .collect(Collectors.toList());
        internshipRepository.saveAll(internships);

        // Save structured Certificates
        List<Certificate> certificates = dto.getCertificates().stream()
                .map(c -> Certificate.builder()
                        .courseName(c.getCourseName())
                        .platform(c.getPlatform())
                        .completionDate(c.getCompletionDate())
                        .certificateLink(c.getCertificateLink())
                        .user(user)
                        .build())
                .collect(Collectors.toList());
        certificateRepository.saveAll(certificates);

        // Save summary in Resume table
        Resume resume = resumeRepository.findByUser(user).orElse(new Resume());
        resume.setUser(user);
        resume.setSkills(dto.getSkills().stream()
                .map(SkillDTO::getSkillName)
                .collect(Collectors.joining(", ")));
        resume.setProjects(dto.getProjects().stream()
                .map(ProjectDTO::getTitle)
                .collect(Collectors.joining(", ")));
        resume.setInternships(dto.getInternships().stream()
                .map(InternshipDTO::getRole)
                .collect(Collectors.joining(", ")));
        resume.setCertificates(dto.getCertificates().stream()
                .map(CertificateDTO::getCourseName)
                .collect(Collectors.joining(", ")));
        resumeRepository.save(resume);
    }

    public ResumeDTO getResumeByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Optional<Resume> optionalResume = resumeRepository.findByUser(user);

        if (optionalResume.isEmpty()) {
            // Return an empty resume response (no crash)
            return ResumeDTO.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .projects(Collections.emptyList())
                    .skills(Collections.emptyList())
                    .internships(Collections.emptyList())
                    .certificates(Collections.emptyList())
                    .build();
        }

        Resume resume = optionalResume.get();

        return ResumeDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .projects(projectRepository.findByUser(user)
                        .stream()
                        .map(p -> ProjectDTO.builder()
                                .title(p.getTitle())
                                .description(p.getDescription())
                                .techStack(p.getTechStack())
                                .link(p.getLink())
                                .build())
                        .toList())
                .skills(skillRepository.findByUser(user)
                        .stream()
                        .map(s -> SkillDTO.builder()
                                .skillName(s.getSkillName())
                                .proficiencyLevel(s.getProficiencyLevel())
                                .build())
                        .toList())
                .internships(internshipRepository.findByUser(user)
                        .stream()
                        .map(i -> InternshipDTO.builder()
                                .role(i.getRole())
                                .company(i.getCompany())
                                .startDate(i.getStartDate())
                                .endDate(i.getEndDate())
                                .contribution(i.getContribution())
                                .build())
                        .toList())
                .certificates(certificateRepository.findByUser(user)
                        .stream()
                        .map(c -> CertificateDTO.builder()
                                .courseName(c.getCourseName())
                                .platform(c.getPlatform())
                                .completionDate(c.getCompletionDate())
                                .certificateLink(c.getCertificateLink())
                                .build())
                        .toList())
                .build();
    }

}
