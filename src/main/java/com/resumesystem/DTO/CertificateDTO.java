package com.resumesystem.DTO;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateDTO {
    private String courseName;
    private String platform;
    private LocalDate completionDate;
    private String certificateLink;
}
