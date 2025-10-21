package com.resumesystem.DTO;

import lombok.*;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResumeDTO {
    private String name;
    private String email;
    private List<ProjectDTO> projects;
    private List<SkillDTO> skills;
    private List<InternshipDTO> internships;
    private List<CertificateDTO> certificates;
}



