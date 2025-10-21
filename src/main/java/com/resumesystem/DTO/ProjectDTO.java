package com.resumesystem.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
    private String title;
    private String description;
    private String link;
    private String techStack;
}

