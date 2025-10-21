package com.resumesystem.DTO;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipDTO {
    private String role;
    private String company;
    private LocalDate startDate;
    private LocalDate endDate;
    private String contribution;
}

