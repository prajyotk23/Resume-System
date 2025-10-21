package com.resumesystem.DTO;

import java.util.List;

import com.resumesystem.Entity.Certificate;
import com.resumesystem.Entity.Internship;
import com.resumesystem.Entity.Project;
import com.resumesystem.Entity.Skill;
import com.resumesystem.Entity.User;
import com.resumesystem.Enum.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;

    private List<Project> projects;
    private List<Skill> skills;
    private List<Internship> internships;
    private List<Certificate> certificates;

    public static UserDTO from(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .projects(user.getProjects())
                .skills(user.getSkills())
                .internships(user.getInternships())
                .certificates(user.getCertificates())
                .build();
    }
}


